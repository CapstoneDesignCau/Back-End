package capstone.capstone01.global.exception;


import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.ErrorReasonDTO;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatusCode;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomApiResponse<?>> validation(ConstraintViolationException e, WebRequest request) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""))
                .collect(Collectors.joining(", "));

        return handleExceptionInternalArgs(e, headers, ErrorStatus._BAD_REQUEST, request, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<?>> exception(Exception e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(), request, e.getMessage());
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomApiResponse<?>> onThrowException(GeneralException generalException, HttpServletRequest request) {
        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();
        return handleExceptionInternal(generalException, errorReasonHttpStatus, null, request);
    }

    private ResponseEntity<CustomApiResponse<?>> handleExceptionInternal(Exception e, ErrorReasonDTO reason,
                                                                         HttpHeaders headers, HttpServletRequest request) {

        CustomApiResponse<Object> body = CustomApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);
        WebRequest webRequest = new ServletWebRequest(request);
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
        return new ResponseEntity<>(body, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }

    private ResponseEntity<CustomApiResponse<?>> handleExceptionInternalFalse(Exception e, ErrorStatus errorCommonStatus,
                                                                              HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
        CustomApiResponse<Object> body = CustomApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), errorPoint);
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
        return new ResponseEntity<>(body, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }

    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers, ErrorStatus errorCommonStatus,
                                                               WebRequest request, String errorMessage) {
        CustomApiResponse<Object> body = CustomApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage() + ": " + errorMessage, null);
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
        return new ResponseEntity<>(body, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }

    private ResponseEntity<CustomApiResponse<?>> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
                                                                                   HttpHeaders headers, WebRequest request) {
        CustomApiResponse<Object> body = CustomApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
        return new ResponseEntity<>(body, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
}