package capstone.capstone01.domain.hashtag.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashtagCreateRequestDto {

    @NotBlank(message = "해시태그 이름을 입력해주세요.")
    @Size(max = 50, message = "해시태그의 최대 길이 제한은 50입니다.")
    @Schema(description = "해시태그 이름", example = "example")
    private String name;

}