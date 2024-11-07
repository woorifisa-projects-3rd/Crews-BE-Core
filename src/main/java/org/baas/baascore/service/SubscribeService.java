package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.baas.baascore.dto.SubscribeRequestDto;
import org.baas.baascore.dto.SubscribeResponseDto;
import org.baas.baascore.excaption.BankNotFoundException;
import org.baas.baascore.excaption.ErrorCode;
import org.baas.baascore.excaption.HashingAlgorithmNotFoundException;
import org.baas.baascore.model.Bank;
import org.baas.baascore.model.Subscribe;
import org.baas.baascore.repository.BankRepository;
import org.baas.baascore.repository.SubscribeRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final BankRepository bankRepository;

    public SubscribeResponseDto createSubscription(SubscribeRequestDto requestDto) {

        if (isValidBusinessNumber(requestDto.businessNum())) {
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


    /**
     * 액세스 키와 시크릿 키를 검증하는 메서드
     */
    public boolean validateKeys(String accessKey, String secretKey) {
        // 액세스 키로 구독 정보를 조회합니다.
        Optional<Subscribe> optionalSubscribe = subscribeRepository.findByAccessKey(accessKey);

        if (optionalSubscribe.isPresent()) {
            Subscribe subscribe = optionalSubscribe.get();

            // 입력된 시크릿 키를 해시화합니다.
            String hashedInputSecretKey = hashSecretKey(secretKey);

            // 데이터베이스에 저장된 시크릿 키:받은 시크릿키 비교
            return subscribe.getSecretKeyHash().equals(hashedInputSecretKey);
        }

        // 해당 액세스 키가 없으면 false 반환
        return false;
    }

    /**
     * 시크릿 키를 해시화하는 메서드
     */
    private String hashSecretKey(String secretKey) {
        try {
            // SHA-256 알고리즘을 사용하여 해시화
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            // Base64로 인코딩하여 문자열로 변환
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new HashingAlgorithmNotFoundException(ErrorCode.HASH_ALGORITHM_NOT_FOUND);

        }
    }

    // 유효한 사업자등록번호인지 확인하는 메서드
    private boolean isValidBusinessNumber(String businessNum) {
        // 사업자등록번호가 10자리 숫자로 구성되어 있는지 확인
        return businessNum != null && businessNum.matches("\\d{10}");
    }
}
