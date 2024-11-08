package org.baas.baascore.util;

import lombok.extern.slf4j.Slf4j;
import org.baas.baascore.excaption.ErrorCode;
import org.baas.baascore.excaption.HashingAlgorithmNotFoundException;
import org.baas.baascore.model.Subscribe;
import org.baas.baascore.repository.SubscribeRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
public class SecurityUtils {
    private SecurityUtils() {
        throw new UnsupportedOperationException("Utility class");
    }


    // SHA-256 해시화 메서드
    public static String hashSecretKey(String secretKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new HashingAlgorithmNotFoundException(ErrorCode.HASH_ALGORITHM_NOT_FOUND);
        }
    }

    // 사업자등록번호 유효성 검증 메서드
    public static boolean isValidBusinessNumber(String businessNum) {
        return businessNum != null && businessNum.matches("\\d{10}");
    }

    // API 키와 시크릿 키 검증 메서드
    public static boolean validateKeys(String accessKey, String secretKey, SubscribeRepository subscribeRepository) {
        Optional<Subscribe> optionalSubscribe = subscribeRepository.findByAccessKey(accessKey);

        if (optionalSubscribe.isPresent()) {
            Subscribe subscribe = optionalSubscribe.get();
            log.info("이건가 {}",subscribe.getSecretKeyHash());
            String hashedInputSecretKey = hashSecretKey(secretKey);
            return subscribe.getSecretKeyHash().equals(hashedInputSecretKey);
        }

        return false;
    }
}
