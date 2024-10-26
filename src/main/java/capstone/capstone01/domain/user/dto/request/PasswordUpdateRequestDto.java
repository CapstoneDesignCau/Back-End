package capstone.capstone01.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateRequestDto {

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    @Size(min = 8, max = 16, message = "패스워드는 8자 이상 16자 이하로 입력해야 합니다.")
    @Schema(description = "현재 비밀번호", example = "currentPassword123")
    private String currentPassword;

    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    @Size(min = 8, max = 16, message = "패스워드는 8자 이상 16자 이하로 입력해야 합니다.")
    @Schema(description = "새 비밀번호", example = "newPassword123")
    private String newPassword;

}