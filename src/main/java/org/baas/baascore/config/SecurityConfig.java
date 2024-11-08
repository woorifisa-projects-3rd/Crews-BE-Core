package org.baas.baascore.config;

import org.baas.baascore.repository.SubscribeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(1) // 시큐리티 설정 우선순위 설정
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable) // 폼 기반 로그인 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 인증 비활성화
                .anonymous(AbstractHttpConfigurer::disable) // 익명 인증 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/auth/api-keys", "/index.html",
                                "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**")
                        .permitAll()
                        .anyRequest().authenticated() // 나머지 경로는 인증 필요
                )
                .addFilterAt(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationFilter(SubscribeRepository subscribeRepository) {
        return new ApiKeyAuthenticationFilter(subscribeRepository);
    }


}

