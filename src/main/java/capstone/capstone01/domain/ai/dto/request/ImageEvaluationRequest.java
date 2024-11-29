package capstone.capstone01.domain.ai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageEvaluationRequest {

    @Schema(description = "이미지 URL", example = "http://example.com/image.jpg")
    private String image_url;

    @Schema(description = "이미지 평가 ID", example = "1")
    private Long image_evaluation_id;
}