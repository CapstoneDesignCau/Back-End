package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.dto.request.CommentCreateRequestDto;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.user.domain.User;

import static capstone.capstone01.global.util.value.StaticValue.DEFAULT_PROFILE_IMAGE_URL;


public class CommentConverter {

    public static Comment toComment(CommentCreateRequestDto requestDto, User writer, Post post) {
        return Comment.builder()
                .content(requestDto.getContent())
                .writer(writer)
                .post(post)
                .isDeleted(false)
                .build();
    }

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .userProfileUrl(comment.getWriter().getProfileImage() != null ? comment.getWriter().getProfileImage().getFileUrl() : DEFAULT_PROFILE_IMAGE_URL)
                .writerNickname((comment.getWriter().getNickname()))
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .likeCount(comment.getLikeCount())
                .isDeleted(comment.getIsDeleted())
                .build();
    }

}
