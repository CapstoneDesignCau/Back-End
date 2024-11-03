package capstone.capstone01.domain.imageevaluation.dto.response;

import capstone.capstone01.domain.feedback.domain.dto.request.FeedbackResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ImageEvaluationResponseDto {

    @Schema(description = "이미지 평가 ID", example = "1")
    private Long id;

    @Schema(description = "평가 점수", example = "85")
    private int score;

    @Schema(description = "평가 완료 여부", example = "true")
    private boolean isFinish;

    @Schema(description = "피드백 목록")
    private List<FeedbackResponseDto> feedbacks;

    @Schema(description = "평가 이미지 URL", example = "http://example.com/image.jpg")
    private String evaluationImageUrl;

    @Schema(description = "파일명", example = "example.jpg")
    private String fileName;

    @Schema(description = "확장자", example = "jpg")
    private String extension;

    @Schema(description = "생성 날짜", example = "2024-11-02T23:10:01")
    private LocalDateTime createdAt;

}