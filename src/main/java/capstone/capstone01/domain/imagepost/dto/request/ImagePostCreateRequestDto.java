package capstone.capstone01.domain.imagepost.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagePostCreateRequestDto {

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    @Schema(description = "게시글 제목", example = "test 게시글입니다.")
    private String title;

    @NotNull(message = "게시글 공개 여부를 입력해주세요.")
    @Schema(description = "게시글 공개 여부", example = "true")
    private Boolean isOpen;

}
