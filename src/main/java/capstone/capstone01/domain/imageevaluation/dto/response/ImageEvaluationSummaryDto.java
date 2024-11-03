package capstone.capstone01.domain.imageevaluation.dto.response;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Builder
public class ImageEvaluationSummaryDto {

    @Schema(description = "이미지 평가 ID", example = "1")
    private Long id;

    @Schema(description = "파일명", example = "example.jpg")
    private String fileName;

    @Schema(description = "확장자", example = "jpg")
    private String extension;

    @Schema(description = "사진 URL", example = "http://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "생성 날짜", example = "2024-11-02T23:10:01")
    private LocalDateTime createdAt;

    @Schema(description = "평가 완료 여부", example = "true")
    private boolean isFinish;

}