package capstone.capstone01.domain.learningmaterial.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningMaterialCreateRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Schema(description = "제목", example = "학습 자료 제목")
    private String title;

    @NotBlank(message = "참고 정보를 입력해주세요.")
    @Schema(description = "참고 정보", example = "이럴 때 참고해요.")
    private String referenceInfo;

    @NotBlank(message = "팁을 입력해주세요.")
    @Schema(description = "팁", example = "추가 꿀팁")
    private String tips;

    @NotBlank(message = "사진 예쁘게 찍는 방법을 입력해주세요.")
    @Schema(description = "사진 예쁘게 찍는 방법", example = "사진 예쁘게 찍는 방법")
    private String prettyManner;

    @Schema(description = "해시태그 목록", example = "[\"tag1\", \"tag2\"]")
    private List<String> hashtags;

}