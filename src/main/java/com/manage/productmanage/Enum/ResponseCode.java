package com.manage.productmanage.Enum;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

/**
 * 작업에 성공 할 경우 출력될 HttpStatus, HttpCode, 상태 메세지
 */
@Getter
public enum ResponseCode {
    
    SUCCESS_GET(HttpStatus.OK , 200, "successfully get"),           // 성공적으로 조회 완료
    SUCCESS_POST(HttpStatus.CREATED , 201,"successfully post"),     // 성공적으로 생성 완료 
    SUCCESS_PATCH(HttpStatus.OK , 200,"successfully edit"),         // 성공적으로 수정 완료
    SUCCESS_DELETE(HttpStatus.OK , 200,"successfully delete");      // 성공적으로 삭제 완료

    private final HttpStatusCode status; // http 상태
    private final int code;              // http 상태 코드
    private final String message;        // 작업 후 메세지

    ResponseCode(HttpStatusCode status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
