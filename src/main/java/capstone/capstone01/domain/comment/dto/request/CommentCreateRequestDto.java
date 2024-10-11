package capstone.capstone01.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequestDto {

    @Schema(description = "이미지 게시물 ID", example = "1")
    private Long imagePostId;

    @Schema(description = "댓글 내용", example = "This is a comment.")
    private String content;

}
