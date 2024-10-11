package capstone.capstone01.domain.comment.controller;

import capstone.capstone01.domain.comment.dto.request.CommentCreateRequestDto;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.comment.service.CommentService;
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
@RequestMapping("/api/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글 생성 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public CustomApiResponse<Long> createComment(
            @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 로그인한 사용자의 이메일(ID)를 가져옴.

        Long commentId = commentService.createComment(email, commentCreateRequestDto);
        return CustomApiResponse.of(SuccessStatus.COMMENT_CREATED, commentId);
    }

    @Operation(summary = "댓글 조회", description = "댓글 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{comment-Id}")
    public CustomApiResponse<CommentResponseDto> getComment(@PathVariable("comment-Id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CommentResponseDto commentResponseDto = commentService.getComment(email, id);
        return CustomApiResponse.of(SuccessStatus.COMMENT_OK, commentResponseDto);
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{comment-Id}")
    public CustomApiResponse<Void> deleteComment(@PathVariable("comment-Id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        commentService.deleteComment(email, id);
        return CustomApiResponse.of(SuccessStatus.COMMENT_OK, null);
    }
}
