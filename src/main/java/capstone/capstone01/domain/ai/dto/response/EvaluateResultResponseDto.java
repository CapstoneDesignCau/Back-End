package capstone.capstone01.domain.ai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EvaluateResultResponseDto {

    @Schema(description = "이미지 평가 ID", example = "1")
    private Long imageEvaluationId;

    @Schema(description = "총 점수", example = "85")
    private int totalScore;

    @Schema(description = "부분 점수 목록")
    private List<PartScoreDto> partScores;

}