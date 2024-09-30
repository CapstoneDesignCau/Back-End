package capstone.capstone01.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // BCrypt 방식의 비번 암호화를 위한 인코더 반환
        return new BCryptPasswordEncoder();
    }

}