package capstone.capstone01.domain.user.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NicknameUpdateRequestDto {

    @NotBlank(message = "변경할 닉네임을 입력해주세요.")
    @Schema(description = "닉네임", example = "puang123")
    private String nickname;

}
