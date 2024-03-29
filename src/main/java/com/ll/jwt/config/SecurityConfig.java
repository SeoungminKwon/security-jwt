package com.ll.jwt.config;

import com.ll.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class)
                .csrf(csrf ->
                        csrf.disable()
                ).sessionManagement(
                        session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 x
                )
                .addFilter(corsFilter) // 컨트롤러 - @CrossOrigin(인증 x), 필터에 등록 (인증 o)
                .formLogin(
                        formLogin ->
                                formLogin.disable()
                )
                .httpBasic(
                        httpBasic -> httpBasic.disable()
                )
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN", "MANAGER")
                                        .requestMatchers("/api/v1/manager/**").hasAnyRole("ADMIN", "MANAGER")
                                        .requestMatchers("/api/v1/admin/**").hasRole("USER")
                                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
