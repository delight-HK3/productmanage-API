package com.manage.productmanage.exception;

import com.manage.productmanage.Enum.ExceptionCode;

import lombok.Getter;

/**
 * 조회시 결과가 없는 경우 Exception
 */
@Getter
public class NoSearchException extends RuntimeException{
    
    private ExceptionCode error;

    public NoSearchException(ExceptionCode e) {
        super(e.getMessage());
        this.error = e;
    }

}
