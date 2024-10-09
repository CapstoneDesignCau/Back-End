package capstone.capstone01.global.apipayload.code.status;


import capstone.capstone01.global.apipayload.code.BaseErrorCode;
import capstone.capstone01.global.apipayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    //일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 회원 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER_4001", "해당하는 이메일의 사용자가 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_4002", "이미 존재하는 이메일 입니다."),
    USER_EMAIL_NULL(HttpStatus.BAD_REQUEST, "USER_4003", "사용자 아이디는 필수 입니다."),
    USER_NAME_NULL(HttpStatus.BAD_REQUEST, "USER_4004", "닉네임 입력은 필수 입니다."),
    USER_INCORRECT_PW(HttpStatus.FORBIDDEN, "USER_4005", "비밀번호가 일치하지 않습니다."),
    USER_NICKNAME_EXISTS(HttpStatus.CONFLICT, "USER_4006", "이미 존재하는 닉네임 입니다."),

    //게시물(Post 관련 에러)
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST_4001", "해당하는 게시물이 없습니다."),
    POST_EDIT_NOT_ALLOWED(HttpStatus.UNAUTHORIZED, "POST_4002", "게시물은 작성자만 변경할수 있습니다."),

    //댓글 관련 에러
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "COMMENT_4001", "해당하는 댓글이 없습니다."),

    //좋아요 관련 에러
    POST_ALREADY_LIKE(HttpStatus.BAD_REQUEST, "LIKE_4001", "해당하는 게시글은 이미 좋아요인 상태입니다."),
    POST_LIKE_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "LIKE_4002", "해당하는 게시글은 이미 좋아요가 취소 된 상태입니다."),
    POST_LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "LIKE_4003", "해당하는 게시글의 좋아요 내역을 찾을 수 없습니다."),
    COMMENT_ALREADY_LIKE(HttpStatus.BAD_REQUEST, "LIKE_4004", "해당하는 댓글은 이미 좋아요인 상태입니다."),
    COMMENT_LIKE_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "LIKE_4005", "해당하는 댓글은 이미 좋아요가 취소 된 상태입니다."),
    COMMENT_LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "LIKE_4006", "해당하는 댓글의 좋아요 내역을 찾을 수 없습니다."),

    //파일 관련 에러
    FILE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "FILE_4001", "S3에 파일을 저장하는 것을 실패했습니다."),
    FILE_DELETE_FAIL(HttpStatus.BAD_REQUEST, "FILE_4002", "S3에 파일을 삭제하는 것을 실패했습니다."),
    FILE_COUNT_EXCEED(HttpStatus.BAD_REQUEST, "FILE_4003", "파일의 개수가 너무 많습니다."),
    FILE_NULL(HttpStatus.BAD_REQUEST, "FILE_4004", "파일이 비어있습니다."),
    FILE_SIZE_EXCEED(HttpStatus.BAD_REQUEST, "FILE_4005", "저장가능한 파일의 크기를 초과합니다."),
    FILE_WRONG_EXTENSION(HttpStatus.BAD_REQUEST, "FILE_4006", "허용되지 않은 확장자명입니다."),
    FILE_WRONG_URL(HttpStatus.BAD_REQUEST, "FILE_4007", "잘못된 S3 URL 입니다."),
    FILE_URL_ENCODING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_4008", "UTF-8방식의 인코딩에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}