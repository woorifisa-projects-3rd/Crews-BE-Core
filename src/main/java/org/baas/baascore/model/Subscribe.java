package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.baas.baascore.excaption.ErrorCode;
import org.baas.baascore.excaption.HashingAlgorithmNotFoundException;
import org.baas.baascore.util.BaseTimeEntity;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

/**
 * 구독 및 API 키를 관리하는 Subscribe 엔티티
 */
@Getter
@Entity
@Table(name = "subscribe")
public class Subscribe extends BaseTimeEntity {
    // 구독 정보의 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품 주인 은행 (은행:상품 1:N)
    @ManyToOne
    private Bank bank;

    // 구독상품명
    private String productName;

    // 사업자 등록 번호
    @Column(name = "business_num", nullable = false)
    private String businessNum;

    // 구독회사명
    @Column(name = "company_name", nullable = false)
    private String companyName;

    // 구독만료일자
    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireDate;

    // 현재 구독여부
    @Column(name = "is_subscribe", nullable = false)
    private boolean isSubscribed;

    // 구독료
    @Column(name = "fee_amount", nullable = false)
    private BigDecimal feeAmt;

    // 해당 회사에 발급한 액세스 키 (변경 불가)
    @Column(name = "access_key", nullable = false)
    private String accessKey;

    // 해당 회사에 발급한 시크릿 키 해시 (변경 불가)
    @Column(name = "secret_key", nullable = false)
    private String secretKeyHash;

    // 시크릿 키의 원본을 임시로 저장 (DB에 저장되지 않음)
    @Transient
    private String plainSecretKey;


    /**
     * 액세스 키와 시크릿 키를 설정합니다.
     * 이 메서드는 SubscribeService에서만 호출해야 합니다.
     */
    void assignKeys(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKeyHash = hashSecretKey(secretKey);
        this.plainSecretKey = secretKey;
    }

    // 정적 팩토리 메서드
    public static Subscribe createSubscription(
            Bank bank,
            String productName,
            String businessNum,
            String companyName) {

        Subscribe subscribe = new Subscribe();

        // 입력받은 값 설정
        subscribe.bank = bank;
        subscribe.productName = productName;
        subscribe.businessNum = businessNum;
        subscribe.companyName = companyName;

        // 기타 필드 설정
        subscribe.isSubscribed = true;

        subscribe.expireDate = LocalDateTime.now().plusMonths(12); // 예: 12개월 후 만료
        subscribe.feeAmt = BigDecimal.valueOf(100_000); // 기본 구독료 설정

        // 액세스 키와 시크릿 키 생성 및 설정
        String accessKey = UUID.randomUUID().toString();
        String secretKey = generateSecretKey();

        subscribe.assignKeys(accessKey, secretKey);

        return subscribe;
    }

    // 시크릿 키 생성 메서드
    private static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256비트 키
        secureRandom.nextBytes(key);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(key);
    }

    // 시크릿 키 해시화 메서드
    private static String hashSecretKey(String secretKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new HashingAlgorithmNotFoundException(ErrorCode.HASH_ALGORITHM_NOT_FOUND);
        }
    }

}
