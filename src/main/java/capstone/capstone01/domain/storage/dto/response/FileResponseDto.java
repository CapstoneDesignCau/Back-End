package capstone.capstone01.domain.storage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileResponseDto {

    @Schema(description = "파일 URL", example = "http://example.com/file.jpg")
    private String fileUrl;

    @Schema(description = "파일명", example = "example.jpg")
    private String fileName;

    @Schema(description = "확장자", example = "jpg")
    private String extension;

}