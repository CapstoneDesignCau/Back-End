package capstone.capstone01.domain.hashtag.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HashtagResponseDto {

    @Schema(description = "해시태그 ID", example = "1")
    private Long id;

    @Schema(description = "해시태그 이름", example = "exampleTag")
    private String hashtag;

}