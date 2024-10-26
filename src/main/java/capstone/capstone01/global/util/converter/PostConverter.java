package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;
import capstone.capstone01.domain.post.dto.response.PostSummaryResponseDto;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.global.util.value.StaticValue;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static Post toPost(PostCreateRequestDto requestDto, User writer) {
        return Post.builder()
                .title(requestDto.getTitle())
                .writer(writer)
                .content((requestDto.getContent()))
                .isDeleted(false)
                .build();
    }

    public static PostResponseDto toPostResponseDto(Post post) {
        List<CommentResponseDto> commentResponseDtos = post.getComments().stream()
                .map(CommentConverter::toCommentResponseDto)
                .collect(Collectors.toList());

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writerNickname(post.getWriter().getNickname())
                .writerProfileImageUrl(post.getWriter().getProfileImage() != null ? post.getWriter().getProfileImage().getFileUrl() : StaticValue.DEFAULT_PROFILE_IMAGE_URL)
                .isOpen(post.getIsOpen())
                .isDeleted(post.getIsDeleted())
                .createdAt(post.getCreatedAt())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .comments(commentResponseDtos)
                .build();
    }

    public static PostSummaryResponseDto toPostSummaryResponseDto(Post post) {
        return PostSummaryResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writerNickname(post.getWriter().getNickname())
                .createdAt(post.getCreatedAt())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .build();
    }

}