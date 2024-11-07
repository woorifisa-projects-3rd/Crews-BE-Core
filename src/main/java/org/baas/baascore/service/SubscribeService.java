package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.baas.baascore.dto.SubscribeRequestDto;
import org.baas.baascore.dto.SubscribeResponseDto;
import org.baas.baascore.excaption.BankNotFoundException;
import org.baas.baascore.model.Bank;
import org.baas.baascore.model.Subscribe;
import org.baas.baascore.repository.BankRepository;
import org.baas.baascore.repository.SubscribeRepository;
import org.baas.baascore.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final BankRepository bankRepository;

    public SubscribeResponseDto createSubscription(SubscribeRequestDto requestDto) {

        if (SecurityUtils.isValidBusinessNumber(requestDto.businessNum())) {
            log.info("사업자등록번호 : {} 인증 성공", requestDto.businessNum());
        }

        // Bank 엔티티 조회
        Optional<Bank> foundBank = bankRepository.findById(requestDto.bankId());

        Bank bank;
        if (foundBank.isPresent()) {
            bank = foundBank.get();
        } else {
            throw new BankNotFoundException();
        }
        // 엔티티 생성
        Subscribe subscribe = Subscribe.createSubscription(
                bank,
                requestDto.productName(),
                requestDto.businessNum(),
                requestDto.companyName()
        );

        // 구독 정보 저장
        subscribeRepository.save(subscribe);
        log.info("{}가(사업자등록번호{}) 구독 시작, api 키 발급 완료", subscribe.getCompanyName(), subscribe.getBusinessNum());

        // 클라이언트에게 반환할 DTO 생성
        return new SubscribeResponseDto(subscribe.getAccessKey(), subscribe.getPlainSecretKey());
    }


}
