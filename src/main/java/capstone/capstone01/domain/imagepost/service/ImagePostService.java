package capstone.capstone01.domain.imagepost.service;

import capstone.capstone01.domain.imagepost.dto.request.ImagePostCreateRequestDto;
import capstone.capstone01.domain.imagepost.dto.response.ImagePostResponseDto;

public interface ImagePostService {

    Long createImagePost(String email, ImagePostCreateRequestDto imagePostCreateRequestDto);

    ImagePostResponseDto getImagePost(String email, Long id);

    void deleteImagePost(String email, Long id);

}