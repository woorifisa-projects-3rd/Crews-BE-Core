package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.baas.baascore.dto.*;
import org.baas.baascore.excaption.*;
import org.baas.baascore.model.Account;
import org.baas.baascore.model.Bank;
import org.baas.baascore.model.Card;
import org.baas.baascore.model.Customer;
import org.baas.baascore.repository.AccountRepository;
import org.baas.baascore.repository.BankRepository;
import org.baas.baascore.repository.CardRepository;
import org.baas.baascore.repository.CustomerRepository;
import org.baas.baascore.util.AccountType;
import org.baas.baascore.util.CurrencyType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {
    private static final Random RANDOM = new Random(); // Random 객체를 static 멤버 변수로 선언하여 재사용
    private static final String ONLY_BANK_NUM = "1002"; // 우리은행 계좌번호 앞자리 4글자

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;

    public AccountIssuedResponse accountIssued(AccountIssuedRequest accountIssuedRequest) {
        Customer customer = customerRepository.findByIdentityCode(accountIssuedRequest.getIdentiyCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );
        String fintechUseNum = UUID.randomUUID().toString();
        Bank bank = bankRepository.findByBankCode("020").orElseThrow( //"020 - 우리은행 은행코드
                BankNotFoundException::new);
        String accountNumber = getAccountNumber();
        BigDecimal balance = BigDecimal.ZERO;
        CurrencyType currencyType = CurrencyType.KRW;
        AccountType accountType = AccountType.CREW;
        Account account = Account.builder().customer(customer).bank(bank).accountNumber(accountNumber).balance(balance)
                .currencyType(currencyType).accountType(accountType).fintechUseNum(fintechUseNum).build();
        Account savedAccount = accountRepository.save(account);
        log.info("생성된 계좌번호 : {}, 이름 {}, 식별자번호 {}", accountNumber, customer.getName(), customer.getIdentityCode());
        return AccountIssuedResponse.of(savedAccount);

    }

    public AccountDeleteResponse accountDelete(AccountDeleteRequest accountDeleteRequest) {
        Customer customer = customerRepository.findByIdentityCode(accountDeleteRequest.getIdentiyCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );
        Account account = accountRepository.findByFintechUseNum(accountDeleteRequest.getFintechUseNum()).orElseThrow(
                FintechNumberNotFoundException::new
        );
        if (!customer.equals(account.getCustomer()))
            throw new MemberNotEqualsException();
        else if (account.getBalance().compareTo(BigDecimal.ZERO) != 0)
            throw new BalanceNotZeroException();
        account.accountDeleted(true);
        return AccountDeleteResponse.of(account.isDeleted());
    }

    public AccountInfoResponse accountInfo(AccountInfoRequest accountInfoRequest) {
        Customer customer = customerRepository.findByIdentityCode(accountInfoRequest.getIdentiyCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );
        List<Account> accountList = accountRepository.findByCustomerAndIsDeletedAndAccountType(customer, false, AccountType.PERSONAL);
        List<Card> cardList = cardRepository.findByCustomerAndCardStatusTrue(customer);


        List<AccountIssuedResponse> changedAccountList = accountList.stream().map(AccountIssuedResponse::of).toList();
        List<CardListDTO> chagedCardList = cardList.stream().map(CardListDTO::of).toList();
        return AccountInfoResponse.builder().accountList(changedAccountList).cardList(chagedCardList).build();

    }

    public FintechNumResponse fintechNum(FintechNumRequest fintechNumRequest) {
        Customer customer = customerRepository.findByIdentityCode(fintechNumRequest.getIdentiyCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );

        String accountNumber = fintechNumRequest.getAccountNumber();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                AccountNumberNotFoundException::new
        );
        if (!customer.equals(account.getCustomer()))
            throw new MemberNotEqualsException();

        return FintechNumResponse.of(account);

    }

    @Retryable
    private String getAccountNumber() {
        String accountNumber = createAccountNumber();
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (optionalAccount.isEmpty()) {
            return accountNumber;
        }
        throw new AccountDuplicatedException();
    }

    public static String createAccountNumber() {
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int createNum = RANDOM.nextInt(10); // 0~9 사이의 랜덤 숫자 생성
            randomNum.append(createNum); // StringBuilder에 추가
        }
        return ONLY_BANK_NUM + randomNum.toString(); // 은행 코드와 랜덤 번호 조합하여 반환
    }

    // 고객 ID로 모든 계좌 조회
    public List<Account> findAccountsByCustomerId(Long customerId) {
        // 고객 ID로 계좌 조회 후 반환
        return accountRepository.findByCustomerId(customerId);
    }

    public List<AccountInitResponseDto> findAccountInit(MemberInitRequestDto memberInitRequestDto) {
        // Optional을 사용해 고객을 찾고 예외를 던지도록 간결화
        Customer customer = customerRepository.findByNameAndPhoneNum(
                memberInitRequestDto.getName(),
                memberInitRequestDto.getPhoneNumber()
        ).orElseThrow(() -> new IllegalStateException("해당 이름과 전화번호의 고객을 찾을 수 없습니다."));

        // 고객 ID로 계좌 정보 찾기
        return accountRepository.findByCustomerId(customer.getId()).stream()
                .map(AccountInitResponseDto::fromEntity)
                .toList();
    }
}
