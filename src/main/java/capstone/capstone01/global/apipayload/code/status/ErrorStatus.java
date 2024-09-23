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
    POST_EDIT_NOT_ALLOWED(HttpStatus.UNAUTHORIZED, "POST_4002", "게시물은 작성자만 변경할수 있습니다.");

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