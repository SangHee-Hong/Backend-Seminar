package com.example.seminar.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.seminar.exception.HttpStatus.BAD_REQUEST;
import static com.example.seminar.exception.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @GetMapping
    public void getRunTimeException() {
        throw new RuntimeException("getRuntimeException 메서드 호출");
    }

    @GetMapping("/custom")
    public void getCustomException() throws CustomException {
        throw new CustomException(INTERNAL_SERVER_ERROR, "getCustomException 메서드 호출");
    }


    @GetMapping("/test/illegal")
    public String throwError() {
        throw new IllegalArgumentException("잘못된 요청 값입니다!");
    }

    @GetMapping("/test/null")
    public String throwNullPointer() {
        String str = null;
        return str.toString();
    }

}

//private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);


// IllegalArgumentException 처리
    /*@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException e) {
        logger.error("IllegalArgumentException 발생: {}", e.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorType", "IllegalArgumentException");
        errorResponse.put("errorCode", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("errorMessage", e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Error-Type", "IllegalArgumentException");

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }

    // 그 외 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception e) {
        logger.error("Unhandled Exception 발생: {}", e.getMessage(), e);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorType", e.getClass().getSimpleName());
        errorResponse.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("errorMessage", "서버 내부 오류가 발생했습니다.");

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Error-Type", e.getClass().getSimpleName());

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

