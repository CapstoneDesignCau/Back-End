package capstone.capstone01.domain.imagepost.controller;

import capstone.capstone01.domain.imagepost.dto.request.PostCreateRequestDto;
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
@RequestMapping("/api/imagePost")
@RestController
public class ImagePostController {

    @Operation(summary = "사진 게시물 생성", description = "사진 게시물 생성 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public CustomApiResponse<Long> createImagePost(
            @Valid @RequestBody PostCreateRequestDto postCreateRequestDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 로그인한 사용자의 이메일(ID)를 가져옴.

        System.out.println(email);
        System.out.println(postCreateRequestDto);
        return CustomApiResponse.of(SuccessStatus.POST_OK, 1L);
    }


}
