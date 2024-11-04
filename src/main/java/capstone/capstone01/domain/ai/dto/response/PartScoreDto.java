package capstone.capstone01.domain.ai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartScoreDto {

    @Schema(description = "각 파트의 점수", example = "20")
    private int score;

    @Schema(description = "점수가 나온 이유", example = "사진의 구도가 좋습니다.")
    private String reason;

}