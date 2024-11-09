package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.dto.CardIssuedResponse;
import org.baas.baascore.dto.CommonRequest;
import org.baas.baascore.excaption.CardDuplicatedException;
import org.baas.baascore.excaption.FintechNumberNotFoundException;
import org.baas.baascore.excaption.IdentityCodeNotFoundException;
import org.baas.baascore.model.Account;
import org.baas.baascore.model.Card;
import org.baas.baascore.model.Customer;
import org.baas.baascore.repository.AccountRepository;
import org.baas.baascore.repository.CardRepository;
import org.baas.baascore.repository.CustomerRepository;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {
    private static final Random RANDOM = new Random(); // Random 객체를 static 멤버 변수로 선언하여 재사용

    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public CardIssuedResponse cardIssued(CommonRequest commonRequest) {
        Customer customer = customerRepository.findByIdentityCode(commonRequest.getIdentityCode()).orElseThrow(
                IdentityCodeNotFoundException::new
        );
        Account account = accountRepository.findByFintechUseNum(commonRequest.getFintechUseNum()).orElseThrow(
                FintechNumberNotFoundException::new
        );
        String cardName = "우리트래블카드";
        String cardNumber = getCardNumber();
        String cvc = createNumber(3, "");
        boolean isIssued = true; //발급여부
        LocalDateTime expiredAt = LocalDateTime.now().plusYears(3);
        boolean cardStatus = true;

        Card card = Card.builder().customer(customer).account(account).cardName(cardName).cardNumber(cardNumber)
                .cvc(cvc).isIssued(isIssued).expiredAt(expiredAt).cardStatus(cardStatus).build();
        Card savedCard = cardRepository.save(card);
        return CardIssuedResponse.from(savedCard);

    }

    @Retryable
    private String getCardNumber() {
        String cardNumber = createNumber(8, "356820");
        Optional<Card> optionalCard = cardRepository.findByCardNumber(cardNumber);

        if(optionalCard.isEmpty()){
            return cardNumber;
        }
        throw new CardDuplicatedException();
    }

    private String createNumber(int count, String prefix) {
        StringBuilder randomNum = new StringBuilder();
        randomNum.append(prefix);
        for (int i = 0; i < count; i++) {
            int createNum = RANDOM.nextInt(10); // 0~9 사이의 랜덤 숫자 생성
            randomNum.append(createNum);
        }
        return randomNum.toString();
    }

}
