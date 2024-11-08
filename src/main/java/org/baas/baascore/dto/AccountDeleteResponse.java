package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountDeleteResponse {
    private String message;

    public static AccountDeleteResponse of(boolean isDeleted){
        String message = "";
        if(isDeleted){
            message = "계좌 삭제가 완료되었습니다.";
        } else {
            message = "계좌 삭제가 실패 했습니다.";
        }
        return AccountDeleteResponse.builder().message(message).build();
    }
}
