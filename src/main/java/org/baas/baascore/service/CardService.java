package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.dto.CardIssuedResponse;
import org.baas.baascore.dto.CommonRequest;
import org.baas.baascore.excaption.AccountDuplicatedException;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {
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
        Random random = new Random();
        int createNum = 0;
        String ranNum = "";
        String randomNum = "";
        for (int i = 0; i < count; i++) {
            createNum = random.nextInt(9);
            ranNum = Integer.toString(createNum);
            randomNum += ranNum;
        }
        String accountNum = prefix + randomNum;
        return accountNum;
    }
}
