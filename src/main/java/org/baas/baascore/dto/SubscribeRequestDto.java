package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.baas.baascore.model.Subscribe;

@Getter
@Builder
@NoArgsConstructor
public class SubscribeRequestDto {
    private Long bankId;
    private String productName;
    private String businessNum;
    private String companyName;

    public SubscribeRequestDto(Long bankId, String productName, String businessNum, String companyName) {
        this.bankId = bankId;
        this.productName = productName;
        this.businessNum = businessNum;
        this.companyName = companyName;
    }
    // 엔티티로부터 SubscribeRequestDto를 생성하는 메서드 예시 (필요에 따라 추가)
    public static SubscribeRequestDto fromEntity(Subscribe subscribe) {
        return SubscribeRequestDto.builder()
                .bankId(subscribe.getBank().getId())
                .productName(subscribe.getProductName())
                .businessNum(subscribe.getBusinessNum())
                .companyName(subscribe.getCompanyName())
                .build();
    }
}

