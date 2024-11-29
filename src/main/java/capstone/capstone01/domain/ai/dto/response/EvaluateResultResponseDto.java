package capstone.capstone01.domain.ai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluateResultResponseDto {

    @Schema(description = "총 점수", example = "90")
    private int totalScore;

    @Schema(description = "피드백 목록")
    private List<List<Object>> feedback;

    @Schema(description = "추가 정보", example = "전신 세로 길이: 25 pixels, 얼굴 세로 길이: 10 pixels, 등신 비율: 7 등신")
    private String moreInfo;
}