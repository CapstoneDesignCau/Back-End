package capstone.capstone01.global.apipayload;


import capstone.capstone01.global.apipayload.code.BaseCode;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CustomApiResponse<T> {

    private final Boolean isSuccess;

    private final String code;

    private final String message;

    private LocalDateTime timestamp;

    private T result;

    // 성공한 경우 응답 생성
    public static <T> CustomApiResponse<T> onSuccess(SuccessStatus status, T result){
        return new CustomApiResponse<>(true, status.getCode(), status.getMessage(),LocalDateTime.now(), result);
    }

    public static <T> CustomApiResponse<T> of(BaseCode code, T result){
        return new CustomApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(),LocalDateTime.now(), result);
    }

    // 실패한 경우 응답 생성
    public static <T> CustomApiResponse<T> onFailure(String code, String message, T data){
        return new CustomApiResponse<>(false, code, message, LocalDateTime.now(), data);
    }
}