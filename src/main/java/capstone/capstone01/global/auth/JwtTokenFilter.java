package capstone.capstone01.global.auth;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null && authorization.startsWith("Bearer ")) {
            // JWT 토큰 처리
            String token = authorization.split(" ")[1].trim();
            try {
                //System.out.println("토큰 확인: " + token);
                processJwtToken(token, request);
            } catch (JwtException e) {
                handleException(response, "COMMON401", "Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void processJwtToken(String token, HttpServletRequest request) {
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            throw new JwtException("Token expired");
        }

        String email = JwtTokenUtil.getEmail(token, secretKey);
        User loginUser = userService.getLoginUserByEmail(email);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, null, List.of(new SimpleGrantedAuthority(loginUser.getRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private void handleException(HttpServletResponse response, String code, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("isSuccess", false);
        errorDetails.put("code", code);
        errorDetails.put("message", message);
        errorDetails.put("result", null);
        errorDetails.put("timestamp", LocalDateTime.now().toString());

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorDetails));
    }
}