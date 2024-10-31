package capstone.capstone01.domain.feedback.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackResponseDto {

    @Schema(description = "피드백 ID", example = "1")
    private Long feedbackId;

    @Schema(description = "피드백 내용", example = "This is a feedback content.")
    private String content;

    @Schema(description = "학습 자료 ID", example = "1")
    private Long materialId;

}