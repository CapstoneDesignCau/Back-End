package capstone.capstone01.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDto {

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    @Schema(description = "게시글 제목", example = "test 게시글입니다.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해주세요.")
    @Schema(description = "게시글 내용", example = "게시글 내용")
    private String text;

}
