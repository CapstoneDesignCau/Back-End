package capstone.capstone01.domain.imageevaluation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ImageEvaluationStatsDto {

    @Schema(description = "평균 점수", example = "86.6")
    private Double averageScore;

    @Schema(description = "최근 5개의 이미지 점수", example = "[85, 90, 78, 92, 88]")
    private List<Integer> recentScores;

}