package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.dto.SubscribeRequestDto;
import org.baas.baascore.dto.SubscribeResponseDto;
import org.baas.baascore.service.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final SubscribeService subscribeService;

    /**
     * 구독을 통해 API 키를 생성하고 발급합니다.
     *
     * @param subscribeRequestDto 구독 요청을 위한 DTO
     * @return 발급된 API 키 정보가 담긴 SubscribeResponseDto
     * @throws IllegalArgumentException 유효하지 않은 사업자등록번호 등 잘못된 입력값에 대한 예외
     * @throws RuntimeException         구독 서비스 처리 중 발생할 수 있는 일반적인 예외
     */
    @PostMapping("/api-keys")
    public ResponseEntity<SubscribeResponseDto> addSubscription(@RequestBody SubscribeRequestDto subscribeRequestDto) {
        // 구독 요청 처리 및 API 키 발급
        SubscribeResponseDto responseDto = subscribeService.createSubscription(subscribeRequestDto);

        // 발급된 API 키 정보를 ResponseEntity로 반환
        return ResponseEntity.ok(responseDto);
    }

}
