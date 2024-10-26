package capstone.capstone01.domain.post.service;

import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;
import capstone.capstone01.domain.post.dto.response.PostSummaryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Long createPost(String email, PostCreateRequestDto postCreateRequestDto);

    PostResponseDto getPost(String email, Long id);

    List<PostSummaryResponseDto> getTopPosts();

    Page<PostSummaryResponseDto> getPosts(Pageable pageable);

    void deletePost(String email, Long id);


}