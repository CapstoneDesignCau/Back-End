package capstone.capstone01.domain.comment.service;

import capstone.capstone01.domain.comment.dto.request.CommentCreateRequestDto;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;

public interface CommentService {

    Long createComment(String email, CommentCreateRequestDto commentCreateRequestDto);

    CommentResponseDto getComment(String email, Long id);

    void deleteComment(String email, Long id);

}