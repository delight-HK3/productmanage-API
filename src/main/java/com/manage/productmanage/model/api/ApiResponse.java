package com.manage.productmanage.model.api;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
/**
 * API Result
 * API 요청시 응답에 사용하는 객체
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;

    private T data;         
    private String message; 

    // 조회에 성공 (리스트 리턴)
    public ApiResponse(int code, T data, String message){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    // API 조회 결과 출력 (조회 결과 리턴)
    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatusCode status, int code, T data, String message) {
        return ResponseEntity.status(status)
                             .body(new ApiResponse<T>(code,data,message));
    }

    // API 조회 결과 출력 (생성,수정,삭제 성공)
    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatusCode status, int code, String message) {
        return ResponseEntity.status(status)
                             .body(new ApiResponse<T>(code,message));
    }
    
    // API 조회 결과 출력 (조회 실패)
    public static <T> ResponseEntity<ApiResponse<T>> fail(HttpStatusCode status, int code,String message) {
        return ResponseEntity.status(status)
                                 .body(new ApiResponse<T>(code,message));
    }
 
    // API 조회 결과 출력 (API 에러)
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatusCode status, int code,String message) {
        return ResponseEntity.status(status)
                                 .body(new ApiResponse<T>(code,message));
    }
}
