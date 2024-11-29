package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;
import capstone.capstone01.domain.post.dto.response.PostSummaryResponseDto;
import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.dto.response.FileResponseDto;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.global.util.value.StaticValue;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static Post toPost(PostCreateRequestDto requestDto, User writer, List<FileSaveInfo> savedFiles) {
        return Post.builder()
                .title(requestDto.getTitle())
                .writer(writer)
                .content(requestDto.getContent())
                .isDeleted(false)
                .files(savedFiles)
                .build();
    }

    public static PostResponseDto toPostResponseDto(Post post, List<CommentResponseDto> commentResponseDtoList, boolean isLikedByUser) {
        List<FileResponseDto> fileResponseDto = post.getFiles().stream()
                .map(StorageConverter::toFileResponseDto)
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
                .comments(commentResponseDtoList)
                .files(fileResponseDto)
                .isLikedByUser(isLikedByUser)
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
                .hasFiles(!post.getFiles().isEmpty())
                .build();
    }

}