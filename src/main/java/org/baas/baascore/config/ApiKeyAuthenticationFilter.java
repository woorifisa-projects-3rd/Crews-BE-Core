package org.baas.baascore.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.baas.baascore.repository.SubscribeRepository;
import org.baas.baascore.util.SecurityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final SubscribeRepository subscribeRepository;

    // SubscribeRepository 주입
    public ApiKeyAuthenticationFilter(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
        log.info("ApiKeyAuthenticationFilter 생성됨");

    }


    // 인증이 필요 없는 경로를 지정
    private static final List<String> EXCLUDE_URLS = Arrays.asList(
            "/v1/auth/api-keys",
            "/index.html",
            "/swagger-ui/",
            "/v3/api-docs",
            "/v3/api-docs/",
            "/swagger-resources/"
    );

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String path = request.getRequestURI();
        log.info("현재 경로: {}", path); // 현재 요청 경로를 출력
        // EXCLUDE_URLS에 포함된 경로로 시작하면 필터를 적용하지 않음
        return EXCLUDE_URLS.stream().anyMatch(path::startsWith);

    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String accessKey = request.getHeader("X-ACCESS-KEY");
        String secretKey = request.getHeader("X-SECRET-KEY");


        if (accessKey != null && secretKey != null) {
            if (SecurityUtils.validateKeys(accessKey, secretKey, subscribeRepository)) {
                // 인증 성공 시 SecurityContext에 인증 정보 설정
                Authentication auth = new UsernamePasswordAuthenticationToken(accessKey, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("SecurityContext에 인증 정보 설정됨: {}", SecurityContextHolder.getContext().getAuthentication());
            } else {
                log.warn("유효하지 않은 API-KEY 입니다: X-ACCESS-KEY={}", accessKey);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "유효하지 않은 API 키입니다.");
                return;
            }
        } else {
            log.warn("API 키가 누락되었습니다");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "API 키가 누락되었습니다.");
            return;
        }

        // SecurityContextHolder에 인증이 설정되었는지 추가 확인
        log.info("SecurityContextHolder 확인: {}", SecurityContextHolder.getContext().getAuthentication());

        // 필터 체인 진행
        filterChain.doFilter(request, response);
    }
}