package capstone.capstone01.domain.photorank.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRankUpdateRequestDto {

    @NotNull(message = "등장한 사진 ID 리스트를 입력해주세요.")
    private List<Long> appearanceIds;

    @NotNull(message = "선택된 사진 ID를 입력해주세요.")
    private Long selectedId;

}