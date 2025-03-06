package com.manage.productmanage.model.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 장바구니 정보 수정 Request용도 DTO 객체
 */
@Getter
@Setter
public class CartUpdtRequestDTO {

    @NotNull
    private Long cartid;        // 장바구니 ID (요청)
    
    @NotNull
    private Long stock;         // 상품 재고 (요청)
}
