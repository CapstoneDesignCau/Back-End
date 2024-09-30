package capstone.capstone01.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final int MIN_KEY_LENGTH = 32; // HS256 알고리즘 최소 BYTE 조건

    // JWT Token 발급
    public static String createToken(String email, String secretKey, long expireTimeMs) {
        SecretKey key = getSecureKey(secretKey);

        return Jwts.builder()
                .claim("Email", email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Claim 에서 Email 꺼내기
    public static String getEmail(String token, String secretKey) {
        SecretKey key = getSecureKey(secretKey);
        return extractClaims(token, key).get("Email").toString();
    }

    // 발급된 Token 만료 시간이 지났는지 체크
    public static boolean isExpired(String token, String secretKey) {
        SecretKey key = getSecureKey(secretKey);
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    // SecretKey 사용해 Token parsing 한 후 클레임을 추출해 반환
    private static Claims extractClaims(String token, SecretKey key) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 비밀키가 최소 256 bits 가 되도록 보장
    private static SecretKey getSecureKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < MIN_KEY_LENGTH) {
            byte[] paddedKeyBytes = new byte[MIN_KEY_LENGTH];
            System.arraycopy(keyBytes, 0, paddedKeyBytes, 0, keyBytes.length);
            for (int i = keyBytes.length; i < MIN_KEY_LENGTH; i++) {
                paddedKeyBytes[i] = (byte) i; // Simple padding
            }
            keyBytes = paddedKeyBytes;
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}