package capstone.capstone01.domain.photorank.dto.response;

import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhotoRankResponseDto {

    @Schema(description = "사진 랭크 ID", example = "1")
    private Long id;

    @Schema(description = "등장 횟수", example = "5")
    private int appearanceCount;

    @Schema(description = "선택된 횟수", example = "3")
    private int selectedCount;

    @Schema(description = "파일 정보")
    private FileResponseDto fileResponse;

}