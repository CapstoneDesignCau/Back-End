package capstone.capstone01.domain.learningmaterial.dto.response;

import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class LearningMaterialResponseDto {

    @Schema(description = "학습 자료 ID", example = "1")
    private Long id;

    @Schema(description = "제목", example = "학습 자료 제목")
    private String title;

    @Schema(description = "참고 정보", example = "이럴 때 참고해요.")
    private String referenceInfo;

    @Schema(description = "팁", example = "추가 꿀팁")
    private String tips;

    @Schema(description = "사진 예쁘게 찍는 방법", example = "사진 예쁘게 찍는 방법")
    private String prettyManner;

    @Schema(description = "삭제 여부", example = "false")
    private Boolean isDeleted;

    @Schema(description = "생성 날짜", example = "2024-11-02T23:10:01")
    private LocalDateTime createdAt;

    @Schema(description = "이미지 목록")
    private List<FileResponseDto> images;
}