package capstone.capstone01.domain.post.service;

import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;

public interface PostService {

    Long createPost(String email, PostCreateRequestDto postCreateRequestDto);

    PostResponseDto getPost(String email, Long id);

    void deletePost(String email, Long id);

}