package capstone.capstone01.domain.like.controller;

import capstone.capstone01.domain.like.service.LikeService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/like")
@RestController
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "사진 게시글 좋아요 저장", description = "사진 게시글 좋아요 저장 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/imagePost/{imagePost-id}")
    public CustomApiResponse<Void> likeImagePost(
            @PathVariable("imagePost-id") String id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.likeImagePost(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_OK, null);
    }

    @Operation(summary = "사진 게시글 좋아요 취소 API", description = "사진 게시글 좋아요 취소 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/imagePost/{imagePost-id}")
    public CustomApiResponse<Void> cancelLikePost(
            @PathVariable("imagePost-id") String id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.cancelLikeImagePost(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_CREATED, null);
    }

    @Operation(summary = "댓글 좋아요 저장", description = "댓글 좋아요 저장 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/comment/{imagePost-id}")
    public CustomApiResponse<Void> likeComment(
            @PathVariable("imagePost-id") String id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.likeComment(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_OK, null);
    }

    @Operation(summary = "댓글 좋아요 취소 API", description = "댓글 좋아요 취소 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/comment/{imagePost-id}")
    public CustomApiResponse<Void> cancelLikeComment(
            @PathVariable("imagePost-id") String id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.cancelLikeComment(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_CREATED, null);
    }

}
