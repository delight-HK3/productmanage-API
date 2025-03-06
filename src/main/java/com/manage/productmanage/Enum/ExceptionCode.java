package com.manage.productmanage.Enum;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

/**
 * Exception이 발생한 경우 출력될 HttpStatus, HttpCode, 상태 메세지
 */
@Getter
public enum ExceptionCode {
    
    // 고객관련 메세지
    NO_SEARCH_CUSTOMER(HttpStatus.NOT_FOUND ,404 ,"This customer does not exist."),         // 존재하지 않는 고객 입니다.
    
    // 주문관련 메세지
    NO_SEARCH_ORDERS(HttpStatus.NOT_FOUND ,404 ,"The order list does not exist."),                      // 주문목록이 존재하지 않습니다.
    OVERFLOW_PRODUCT_STOCK(HttpStatus.NOT_FOUND, 404 , "All items in your cart are out of stock."),     // 장바구니의 모든 상품이 재고량을 초과합니다.

    // 결제 관련 메세지
    NO_SEARCH_PAYMENT(HttpStatus.NOT_FOUND ,404 ,"The payment list does not exist."),                   // 결재목록이 존재하지 않습니다.
    ALEADY_PAYMENT_ORDERID(HttpStatus.NOT_FOUND ,404 ,"This order has already been paid."),             // 이미 결제된 주문 입니다.
    NO_ENOUGH_AMOUNT(HttpStatus.NOT_FOUND ,404 ,"The amount you paid is not enough."),                  // 지불한 금액이 충분하지 않습니다.
    NO_MATCH_ORDERID(HttpStatus.NOT_FOUND ,404 ,"There are no orders matching your order number."),     // 주문번호와 일치하는 주문이 없습니다.

    // 상품관련 메세지
    NO_SEARCH_PRODUCT(HttpStatus.NOT_FOUND ,404 ,"This product does not exist."),                                // 존재하지 않는 상품 입니다.
    NO_PRODUCT_HAVE_LIST(HttpStatus.NOT_FOUND ,404 ,"There are currently no products available for purchase."),  // 현재 구매 가능한 상품이 없습니다.
    NO_PRODUCT_ALL_LIST(HttpStatus.NOT_FOUND ,404 ,"There is no product list."),                                 // 상품목록이 존재하지 않습니다.
    
    // 장바구니 관련 메세지
    NO_ENOUGH_STOCK(HttpStatus.NOT_FOUND ,404 ,"There is not enough stock."),                               // 재고가 충분하지 않습니다.
    NO_SEARCH_CART(HttpStatus.NOT_FOUND ,404 ,"There are no items in your shopping cart."),                 // 장바구니에 담은 물건이 없습니다.
    NO_CART_HAVE_PRODUCT(HttpStatus.NOT_FOUND, 404 , "The product does not exist in your shopping cart."),  // 장바구니에 해당 상품이 존재하지 않습니다.

    // 클라이언트 요청관련 메세지
    NO_REQUIRED_ARGUMENT(HttpStatus.BAD_REQUEST,400,"A required parameter is missing."),        // 필수 파라미터가 누락되었습니다.
    NO_TYPE_MISMATCH_ARGUMENT(HttpStatus.BAD_REQUEST,400,"The parameter type is incorrect."),   // 파라미터 타입이 올바르지 않습니다.
    NOT_FOUND_PAGE(HttpStatus.NOT_FOUND, 404, "The request does not exist."),                   // 존재하지 않는 요청입니다.
    NO_MATCH_METHOD(HttpStatus.METHOD_NOT_ALLOWED, 405, "This method is not allowed.");         // 허용되지 않는 메서드 입니다.

    private final HttpStatusCode status; // http 상태
    private final int code;              // http 상태 코드
    private final String message;        // 작업 후 메세지

    ExceptionCode(HttpStatus status, int code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
