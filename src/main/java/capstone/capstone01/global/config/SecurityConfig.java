package capstone.capstone01.global.config;

import capstone.capstone01.domain.user.service.UserService;
import capstone.capstone01.global.auth.JwtTokenFilter;
import capstone.capstone01.global.exception.security.CustomAccessDeniedHandler;
import capstone.capstone01.global.exception.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        // 기본 인증 및 CSRF 보호 비활성화, 세션 관리 정책을 STATELESS로 설정
        //Todo: Cors 정책 설정 추후 변경 필요
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable);

        // 각 API 별 접근 설정 및, JWT token filter 추가
        //Todo: requestMatchers 안 API 확인 필요
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/user/signUp", "/api/user/login", "/api/user/check-duplicate/**").permitAll() // 다음 API 는 권한 상관없이 항상허용
                        .anyRequest().authenticated()) //그 외의 API 는 JWT 로 인증된 권한 필요
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler) // 403 에러 처리
                        .authenticationEntryPoint(authenticationEntryPoint)) // 401 에러 처리
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(userService, secretKey);
    }

}
