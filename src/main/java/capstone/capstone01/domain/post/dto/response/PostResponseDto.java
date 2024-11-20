package capstone.capstone01.domain.post.dto.response;

import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Schema(description = "작성자 프로필 이미지 URL", example = "http://example.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "게시물 공개 여부", example = "true")
    private Boolean isOpen;

    @Schema(description = "게시물 삭제 여부", example = "false")
    private Boolean isDeleted;

    @Schema(description = "게시물 작성일", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "게시물 좋아요 수", example = "10")
    private int likeCount;

    @Schema(description = "게시물 댓글 수", example = "5")
    private int commentCount;

    @Schema(description = "파일 목록")
    private List<FileResponseDto> files;

    @Schema(description = "댓글 목록")
    private List<CommentResponseDto> comments;

    @Schema(description = "사용자가 좋아요를 눌렀는지 여부", example = "true")
    private Boolean isLikedByUser;

}