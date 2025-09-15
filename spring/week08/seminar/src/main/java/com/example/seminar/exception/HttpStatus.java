package com.example.seminar.exception;

public enum HttpStatus {

    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"), // 학교 이메일 검증시 사용
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    CONFLICT(409, "Conflict"); // assignment 수행 시 추가


    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code + " " + message;
    }

    // Method to get HttpStatus by code
    public static HttpStatus valueOf(int statusCode) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.code == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid HTTP Status code: " + statusCode);
    }
}
