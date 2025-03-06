package com.manage.productmanage.model.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 장바구니 정보 입력 Request용도 DTO 객체
 */
@Getter
@Setter
public class CartInstRequestDTO{

    @NotNull
    private Long customerid;    // 고객 ID (요청)
    @NotNull
    private Long productid;     // 상품 ID (요청)
    @NotNull
    private Long stock;         // 상품 재고 (요청)

}
