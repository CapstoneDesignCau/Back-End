package capstone.capstone01.global.apipayload.code.status;

import capstone.capstone01.global.apipayload.code.BaseCode;
import capstone.capstone01.global.apipayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    USER_OK(HttpStatus.OK, "USER_2000", "USER 관련 요청이 성공적으로 수행되었습니다."),
    USER_CREATED(HttpStatus.CREATED,"USER_2001", "USER 관련 요청이 성공적으로 생성되었습니다."),

    POST_OK(HttpStatus.OK, "POST_2000", "Post 관련 요청이 성공적으로 수행되었습니다."),

    FILE_UPLOAD_OK(HttpStatus.OK, "FILE_2000", "FILE 관련 요청이 성공적으로 수행되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}