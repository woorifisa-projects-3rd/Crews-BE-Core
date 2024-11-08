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
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;

    public AccountIssuedResponse accountIssued(AccountIssuedRequest accountIssuedRequest){
        Customer customer = customerRepository.findByIdentityCode(accountIssuedRequest.getIdentityCode()).orElseThrow(
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
        return AccountIssuedResponse.from(savedAccount);

    }

    public AccountDeleteResponse accountDelete(AccountDeleteRequest accountDeleteRequest) {
        Customer customer = customerRepository.findByIdentityCode(accountDeleteRequest.getIdentityCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );
        Account account = accountRepository.findByFintechUseNum(accountDeleteRequest.getFintechUseNum()).orElseThrow(
                FintechNumberNotFoundException::new
        );
        if(!customer.equals(account.getCustomer()))
            throw new RuntimeException("계좌주인과 서비스 요청 사람이 다릅니다.");
        else if(account.getBalance().compareTo(BigDecimal.ZERO) != 0)
            throw new RuntimeException("계좌 잔액이 남아있습니다. 잔액을 비워 주세요");
        account.accountDeleted(true);
        return AccountDeleteResponse.from(account.isDeleted());
    }

    public AccountInfoResponse accountInfo(AccountInfoRequest accountInfoRequest) {
        Customer customer = customerRepository.findByIdentityCode(accountInfoRequest.getIdentityCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );
        List<Account> accountList = accountRepository.findByCustomerAndIsDeletedAndAccountType(customer, false, AccountType.PERSONAL);
        List<Card> cardList = cardRepository.findByCustomerAndCardStatusTrue(customer);


        List<AccountIssuedResponse> changedAccountList = accountList.stream().map(AccountIssuedResponse::from).toList();
        List<CardListDTO> chagedCardList = cardList.stream().map(CardListDTO::from).toList();
        return AccountInfoResponse.builder().accountList(changedAccountList).cardList(chagedCardList).build();

    }

    public FintechNumResponse fintechNum(FintechNumRequest fintechNumRequest) {
        Customer customer = customerRepository.findByIdentityCode(fintechNumRequest.getIdentityCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );

        String accountNumber = fintechNumRequest.getAccountNumber();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                AccountNumberNotFoundException::new
        );
        if(!customer.equals(account.getCustomer()))
            throw new MemberNotEqualsException();

        return FintechNumResponse.from(account);

    }
    @Retryable
    private String getAccountNumber() {
        String accountNumber = createAccountNumber();
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if(optionalAccount.isEmpty()){
            return accountNumber;
        }
        throw new AccountDuplicatedException();
    }

    private String createAccountNumber() {
        Random random = new Random();
        int createNum = 0;
        String ranNum = "";
        String randomNum = "";
        for (int i = 0; i < 9; i++) {
            createNum = random.nextInt(9);
            ranNum = Integer.toString(createNum);
            randomNum += ranNum;
        }
        String onlyBankNum = "1002"; //우리은행 계좌번호 앞자리 4글자
        String accountNum = onlyBankNum + randomNum;
        return accountNum;
    }
}
