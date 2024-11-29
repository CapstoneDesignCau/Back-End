package capstone.capstone01.domain.ai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {

    @Schema(description = "피드백 ID", example = "1")
    private int id;

    @Schema(description = "피드백 메시지", example = "아웃포커싱 효과를 활용하면 인물을 더 돋보이게 할 수 있습니다.")
    private String message;

}