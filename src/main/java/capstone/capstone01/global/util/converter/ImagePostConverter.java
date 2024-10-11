package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.imagepost.domain.ImagePost;
import capstone.capstone01.domain.imagepost.dto.request.ImagePostCreateRequestDto;
import capstone.capstone01.domain.imagepost.dto.response.ImagePostResponseDto;
import capstone.capstone01.domain.user.domain.User;

public class ImagePostConverter {

    public static ImagePost toImagePost(ImagePostCreateRequestDto requestDto, User writer) {
        return ImagePost.builder()
                .title(requestDto.getTitle())
                .writer(writer)
                .isOpen(requestDto.getIsOpen())
                .isDeleted(false)
                .build();
    }

    public static ImagePostResponseDto toImagePostResponseDto(ImagePost imagePost) {
        return ImagePostResponseDto.builder()
                .id(imagePost.getId())
                .title(imagePost.getTitle())
                .writerNickname(imagePost.getWriter().getNickname())
                .isOpen(imagePost.getIsOpen())
                .isDeleted(imagePost.getIsDeleted())
                .build();
    }

}