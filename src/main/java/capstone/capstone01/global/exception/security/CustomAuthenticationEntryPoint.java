package capstone.capstone01.global.exception.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("isSuccess", false);
        errorDetails.put("code", "COMMON401");
        errorDetails.put("message", "인증이 필요합니다: 유효한 자격 증명을 제공하세요.");
        errorDetails.put("result", null);
        errorDetails.put("timestamp", LocalDateTime.now().toString());

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorDetails));
    }
}