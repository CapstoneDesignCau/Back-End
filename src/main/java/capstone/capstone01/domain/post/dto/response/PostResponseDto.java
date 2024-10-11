package capstone.capstone01.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {

    @Schema(description = "게시물 ID", example = "1")
    private Long id;

    @Schema(description = "게시물 제목", example = "test 게시글입니다.")
    private String title;

    @Schema(description = "게시물 내용", example = "게시글 내용입니다.")
    private String content;

    @Schema(description = "작성자 닉네임", example = "개발자푸앙이")
    private String writerNickname;

    @Schema(description = "게시물 공개 여부", example = "true")
    private Boolean isOpen;

    @Schema(description = "게시물 삭제 여부", example = "false")
    private Boolean isDeleted;

}