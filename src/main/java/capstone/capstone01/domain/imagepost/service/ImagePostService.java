package capstone.capstone01.domain.imagepost.service;

import capstone.capstone01.domain.imagepost.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.imagepost.dto.response.ImagePostResponseDto;

public interface ImagePostService {

    Long createImagePost(String email, PostCreateRequestDto postCreateRequestDto);

    ImagePostResponseDto getImagePost(String email, Long id);

    void deleteImagePost(String email, Long id);

}