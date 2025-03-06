package com.manage.productmanage.model.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 상품정보 리턴 DTO 객체
 */
@Getter
@Builder
@ToString
public class ProductResponseDTO {
    
    private final Long id;              // 상품번호
    private final String name;          // 상품이름
    private final String description;   // 상품설명
    private final Long price;           // 상품가격
    private final Long stock;            // 상품재고

}
