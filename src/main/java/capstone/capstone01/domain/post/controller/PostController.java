package capstone.capstone01.domain.post.controller;

import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;
import capstone.capstone01.domain.post.service.PostService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 생성", description = "게시물 생성 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public CustomApiResponse<Long> createPost(
            @Valid @RequestBody PostCreateRequestDto postCreateRequestDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 로그인한 사용자의 이메일(ID)를 가져옴.

        Long postId = postService.createPost(email, postCreateRequestDto);
        return CustomApiResponse.of(SuccessStatus.POST_CREATED, postId);
    }

    @Operation(summary = "사진 게시물 조회", description = "사진 게시물 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{post-id}")
    public CustomApiResponse<PostResponseDto> getPost(@PathVariable("post-id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        PostResponseDto postResponseDto = postService.getPost(email, id);
        return CustomApiResponse.of(SuccessStatus.POST_OK, postResponseDto);
    }

    @Operation(summary = "사진 게시물 삭제", description = "사진 게시물 삭제 API")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{post-id}")
    public CustomApiResponse<Void> deletePost(@PathVariable("post-id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        postService.deletePost(email, id);
        return CustomApiResponse.of(SuccessStatus.POST_OK, null);
    }

}