package com.manage.productmanage.model.order;

import java.util.List;

import lombok.Getter;

/**
 * 주문목록 정보 요청 DTO 객체
 */
@Getter
public class OrderResponseDTO {
    
    private final String orderId;               // 주문 ID
    private final String customerName;          // 고객 이름
    private final String paymentYn;             // 결재 여부
    private List<CartList> cartList;            // 장바구니 목록
    
    // 장바구니 목록 클래스
    @Getter
    public static class CartList {
        private final Long id;                  // 장바구니 ID
        private final String productName;       // 상품 이름
        private final Long productPrice;        // 상품 개당 가격
        private final Long cartStock;           // 장바구니에 담은 물품 수
        private final Long productAllPrice;     // 상품 전체 가격

        public CartList(Long id, String productName, Long productPrice
                        , Long cartStock, Long productAllPrice){
            this.id = id;
            this.productName = productName;
            this.productPrice = productPrice;
            this.cartStock = cartStock;
            this.productAllPrice = productAllPrice;
        }
    }

    public void setCartList(List<CartList> cartList){
        this.cartList = cartList;
    }

    public OrderResponseDTO(String orderId, String customerName, String paymentYn){
        this.orderId = orderId;
        this.customerName = customerName;
        this.paymentYn = paymentYn;
    }
}
