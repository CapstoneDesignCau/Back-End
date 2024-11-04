package capstone.capstone01.domain.imageevaluation.dto.response;

import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Builder
public class ImageEvaluationSummaryDto {

    @Schema(description = "이미지 평가 ID", example = "1")
    private Long id;

    @Schema(description = "평가 이미지 정보")
    private FileResponseDto evaluationImage;

    @Schema(description = "생성 날짜", example = "2024-11-02T23:10:01")
    private LocalDateTime createdAt;

    @Schema(description = "평가 완료 여부", example = "true")
    private boolean isFinish;
}