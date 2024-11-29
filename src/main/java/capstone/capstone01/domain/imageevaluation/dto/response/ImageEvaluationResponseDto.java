package capstone.capstone01.domain.imageevaluation.dto.response;

import capstone.capstone01.domain.feedback.domain.dto.request.FeedbackResponseDto;
import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
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

    @Schema(description = "평가 이미지 정보")
    private FileResponseDto evaluationImage;

    @Schema(description = "생성 날짜", example = "2024-11-02T23:10:01")
    private LocalDateTime createdAt;

    @Schema(description = "사진 평가 관련 더보기 정보", example = "얼굴 명도:132, 등신 비율: 7.8 ~")
    private String moreInfo;

}