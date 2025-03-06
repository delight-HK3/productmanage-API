package com.manage.productmanage.handler;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.manage.productmanage.Enum.ExceptionCode;
import com.manage.productmanage.exception.NoEnoughException;
import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.model.api.ApiResponse;

@RestControllerAdvice
public class ProductManageHandler {
    
    /**
     * 단일 상품 조회시 상품이 없는 경우
     * , 현재 구매 가능한 상품이 존재하지 않는경우
     * , 상품목록이 존재하지 않는경우 Handler
     * 
     * @param <T>
     * @param e
     * @return
     */
    @ExceptionHandler(NoSearchException.class)
    public <T> ResponseEntity<ApiResponse<T>> NoSearchExceptionHandler(NoSearchException e){
        ExceptionCode exceptionCode = e.getError();
        return ApiResponse.fail(exceptionCode.getStatus()
                                , exceptionCode.getCode()
                                , exceptionCode.getMessage());
    }

    /**
     * 장바구니에 있는 모든 상품이 재고보다 많은 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(NoEnoughException.class)
    public <T> ResponseEntity<ApiResponse<T>> NoEnoughExceptionHandler(NoEnoughException e){
        ExceptionCode exceptionCode = e.getError();
        return ApiResponse.fail(exceptionCode.getStatus()
                                , exceptionCode.getCode()
                                , exceptionCode.getMessage());
    }

    /**
     * 파라미터의 타입이 일치하지 않는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public <T> ResponseEntity<ApiResponse<T>> TypeMismatchExceptionHandler(){
        return ApiResponse.fail(ExceptionCode.NO_TYPE_MISMATCH_ARGUMENT.getStatus()
                                , ExceptionCode.NO_TYPE_MISMATCH_ARGUMENT.getCode()
                                , ExceptionCode.NO_TYPE_MISMATCH_ARGUMENT.getMessage());
    }

    /**
     * 필수 파라미터가 NULL인 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
    public <T> ResponseEntity<ApiResponse<T>> NotValidExceptionHandler(){
        return ApiResponse.fail(ExceptionCode.NO_REQUIRED_ARGUMENT.getStatus()
                                , ExceptionCode.NO_REQUIRED_ARGUMENT.getCode()
                                , ExceptionCode.NO_REQUIRED_ARGUMENT.getMessage());
    }

    /**
     * 존재하지 않는 주소를 요청하는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public <T> ResponseEntity<ApiResponse<T>> NoResourceFoundExceptionHandler(){
        return ApiResponse.fail(ExceptionCode.NOT_FOUND_PAGE.getStatus()
                                , ExceptionCode.NOT_FOUND_PAGE.getCode()
                                , ExceptionCode.NOT_FOUND_PAGE.getMessage());
    }

    /**
     * 요청 URL은 맞지만 일치하지 않는 메서드 타입으로 요청하는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, InvalidDataAccessApiUsageException.class})
    public <T> ResponseEntity<ApiResponse<T>> MethodNotSupportHandler(){
        return ApiResponse.fail(ExceptionCode.NO_MATCH_METHOD.getStatus()
                                , ExceptionCode.NO_MATCH_METHOD.getCode()
                                , ExceptionCode.NO_MATCH_METHOD.getMessage());
    }
}
