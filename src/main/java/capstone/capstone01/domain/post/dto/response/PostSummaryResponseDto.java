package capstone.capstone01.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSummaryResponseDto {

    @Schema(description = "게시물 번호", example = "1")
    private Long id;

    @Schema(description = "게시물 제목", example = "test 게시글입니다.")
    private String title;

    @Schema(description = "작성자 닉네임", example = "개발자푸앙이")
    private String writerNickname;

    @Schema(description = "게시물 작성일", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "게시물 좋아요 수", example = "10")
    private int likeCount;

    @Schema(description = "게시물 댓글 수", example = "5")
    private int commentCount;

    @Schema(description = "파일 존재 여부", example = "true")
    private Boolean hasFiles;

}