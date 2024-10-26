package capstone.capstone01.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NicknameUpdateRequestDto {

    @NotBlank(message = "변경할 닉네임을 입력해주세요.")
    @Size(max = 15, message = "닉네임은 최대 15자까지 입력할 수 있습니다.")
    @Schema(description = "닉네임", example = "puang123")
    private String nickname;

}
