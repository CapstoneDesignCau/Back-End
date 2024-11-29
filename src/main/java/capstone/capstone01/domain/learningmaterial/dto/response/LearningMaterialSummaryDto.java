package capstone.capstone01.domain.learningmaterial.dto.response;

import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LearningMaterialSummaryDto {

    @Schema(description = "학습 자료 ID", example = "1")
    private Long id;

    @Schema(description = "제목", example = "학습 자료 제목")
    private String title;

    @Schema(description = "이미지 정보")
    private FileResponseDto image;

    @Schema(description = "삭제 여부", example = "false")
    private Boolean isDeleted;
}