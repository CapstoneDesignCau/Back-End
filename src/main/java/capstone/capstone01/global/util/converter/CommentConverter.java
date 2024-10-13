package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.dto.request.CommentCreateRequestDto;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.user.domain.User;


public class CommentConverter {

    public static Comment toComment(CommentCreateRequestDto requestDto, User writer, Post post) {
        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .writer(writer)
                .post(post)
                .isDeleted(false)
                .build();

        post.addComment(comment); // 양방향 매핑 설정
        return comment;
    }

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writerNickname(comment.getWriter().getNickname())
                .isDeleted(comment.getIsDeleted())
                .build();
    }

}
