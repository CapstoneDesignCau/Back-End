package capstone.capstone01.domain.post.controller;

import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostController {

    @Operation(summary = "게시물 생성", description = "게시물 생성 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public CustomApiResponse<Long> createPost(
            @Valid @RequestBody PostCreateRequestDto postCreateRequestDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 로그인한 사용자의 이메일(ID)를 가져옴. 이걸 Service 에 매개변수로 전달해서 Service 에서 유저 정보 얻기

        System.out.println(email);
        System.out.println(postCreateRequestDto);
        return CustomApiResponse.of(SuccessStatus.POST_OK, 1L);
    }


}
