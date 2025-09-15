package com.example.seminar.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.valueOf;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.error("Advice 내 handleException 호출, {}, {}", request.getRequestURI(), e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    // CustomException을 처리하는 핸들러
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String, String>> handleException(CustomException e, HttpServletRequest request) {
        // 에러 로그 기록
        LOGGER.error("Advice 내 handleException 호출, URI: {}, 메시지: {}",
                request.getRequestURI(), e.getMessage());

        // 예외 정보를 담을 Map 생성
        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatus().getMessage());  // HttpStatus의 설명
        map.put("code", Integer.toString(e.getHttpStatus().getCode())); // HttpStatus 코드
        map.put("message", e.getMessage());  // 예외 메시지

        // ResponseEntity로 반환
        return ResponseEntity
                .status(e.getHttpStatus().getCode())
                .body(map);
    }

    //과제시 추가하면 좋을 메서드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        LOGGER.error("Validation error 발생: URI={}, 메시지={}", request.getRequestURI(), e.getMessage());

        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> response = new HashMap<>();
        response.put("error type", "Validation Failed");
        response.put("code", "400");
        response.put("message", "입력 값 검증 실패");
        response.put("errors", fieldErrors);  // 필드별 에러를 포함

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}