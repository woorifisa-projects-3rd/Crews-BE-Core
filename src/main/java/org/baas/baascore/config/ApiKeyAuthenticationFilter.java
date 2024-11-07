package org.baas.baascore.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.baas.baascore.repository.SubscribeRepository;
import org.baas.baascore.util.SecurityUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final SubscribeRepository subscribeRepository;

    // SubscribeRepository 주입
    public ApiKeyAuthenticationFilter(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }


    // 인증이 필요 없는 경로를 지정
    private static final List<String> EXCLUDE_URLS = Arrays.asList(
            "/v1/auth/api-keys",
            "/",
            "/index.html",
            "/static/",
            "/swagger-ui/",
            "/v3/api-docs/",
            "/swagger-resources/"
    );

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String path = request.getRequestURI();
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
            } else {
                // 인증 실패 시 예외 발생
                throw new BadCredentialsException("유효하지 않은 API 키입니다.");
            }
        } else {
            // API 키가 제공되지 않은 경우 예외 발생
            throw new BadCredentialsException("API 키가 누락되었습니다.");
        }

        filterChain.doFilter(request, response);
    }
}