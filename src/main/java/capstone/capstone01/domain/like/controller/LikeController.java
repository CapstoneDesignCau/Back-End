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

    @Operation(summary = "게시글 좋아요 저장", description = "게시글 좋아요 저장 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/imagePost/{post-id}")
    public CustomApiResponse<Void> likePost(
            @PathVariable("post-id") Long id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.likePost(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_CREATED, null);
    }

    @Operation(summary = "게시글 좋아요 취소", description = "게시글 좋아요 취소 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/imagePost/{post-id}")
    public CustomApiResponse<Void> cancelLikePost(
            @PathVariable("post-id") Long id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.cancelLikePost(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_OK, null);
    }

    @Operation(summary = "댓글 좋아요 저장", description = "댓글 좋아요 저장 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/comment/{comment-id}")
    public CustomApiResponse<Void> likeComment(
            @PathVariable("comment-id") Long id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.likeComment(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_CREATED, null);
    }

    @Operation(summary = "댓글 좋아요 취소", description = "댓글 좋아요 취소 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/comment/{comment-id}")
    public CustomApiResponse<Void> cancelLikeComment(
            @PathVariable("comment-id") Long id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        likeService.cancelLikeComment(email, id);
        return CustomApiResponse.of(SuccessStatus.LIKE_OK, null);
    }

}
