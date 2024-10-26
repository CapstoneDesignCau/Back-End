package capstone.capstone01.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {

    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 내용", example = "테스트 댓글 내용")
    private String content;

    @Schema(description = "작성자 닉네임", example = "푸앙이")
    private String writerNickname;

    @Schema(description = "댓글 삭제 여부", example = "false")
    private Boolean isDeleted;

    @Schema(description = "댓글 좋아요 수", example = "1")
    private int likeCount;

    @Schema(description = "댓글 작성일", example = "2024-01-01")
    private LocalDateTime createdAt;

    @Schema(description = "작성자 프로필 사진 URL", example ="http://example/123")
    private String userProfileUrl;

}