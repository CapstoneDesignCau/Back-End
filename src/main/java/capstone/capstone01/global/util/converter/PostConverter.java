package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;
import capstone.capstone01.domain.user.domain.User;

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
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writerNickname(post.getWriter().getNickname())
                .isOpen(post.getIsOpen())
                .isDeleted(post.getIsDeleted())
                .build();
    }

}