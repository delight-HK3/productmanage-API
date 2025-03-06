package com.manage.productmanage.model.cart;

import lombok.Getter;
import lombok.ToString;

/**
 * 장바구니 정보 리턴 DTO 객체
 */
@Getter
@ToString
public class CartResponseDTO {

    private final Long id;                  // 카트 번호
    private final String customerName;      // 고객 이름
    private final String productName;       // 상품 이름
    private final Long productPrice;        // 상품 개당 가격
    private final Long cartStock;           // 장바구니에 담은 물품 수
    private final Long productAllPrice;     // 상품 전체 가격
    
    public CartResponseDTO(Long id, String customerName, String productName
                    , Long productPrice, Long cartStock, Long productAllPrice){
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.cartStock = cartStock;
        this.productAllPrice = productAllPrice;
    }
}
