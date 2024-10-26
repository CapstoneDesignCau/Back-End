package capstone.capstone01.domain.user.dto.response;

import capstone.capstone01.domain.user.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserInfoResponseDto {

    @Schema(description = "유저 ID", example = "1")
    private Long id;

    @Schema(description = "프로필 이미지 URL", example = "http://example.com/profile.jpg")
    private String profileImageUrl;

    @Schema(description = "이메일", example = "user@example.com")
    private String email;

    @Schema(description = "이름", example = "John Doe")
    private String name;

    @Schema(description = "닉네임", example = "johnny")
    private String nickname;

    @Schema(description = "생년월일", example = "1990-01-01")
    private LocalDate birthday;

    @Schema(description = "성별", example = "MALE")
    private Gender gender;
}
