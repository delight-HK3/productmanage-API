package com.manage.productmanage.exception;

import com.manage.productmanage.Enum.ExceptionCode;

import lombok.Getter;

/**
 * 재고가 충분하지 않은 경우 Exception
 */
@Getter
public class NoEnoughException extends NullPointerException{
    
    private ExceptionCode error;

    public NoEnoughException(ExceptionCode e) {
        super(e.getMessage());
        this.error = e;
    }

}
