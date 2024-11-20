package capstone.capstone01.domain.photorank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRankCreateRequestDto {

    @NotNull(message = "시작 날짜를 입력해주세요.")
    @Schema(description = "시작 날짜", example = "2023-01-01")
    private LocalDate startDate;

    @NotNull(message = "종료 날짜를 입력해주세요.")
    @Schema(description = "종료 날짜", example = "2023-12-31")
    private LocalDate endDate;

}