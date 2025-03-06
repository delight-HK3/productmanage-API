package com.manage.productmanage.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;

import com.manage.productmanage.model.product.ProductResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * product(상품) Entity
 */
@Entity
@Getter
@Table(name="product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "상품 ID (고유 키)")
    private Long id;                    

    @Column(name = "name", length = 255, nullable = false)
    @Comment(value = "상품 이름")
    private String name;

    @Column(name = "description", length = 1000)
    @Comment(value = "상품 설명")
    private String description;

    @Column(name = "price", nullable = false)
    @Comment(value = "상품 가격")
    private Long price;

    @Column(name = "stock ", nullable = false)
    @Comment(value = "재고 수량")
    private Long stock;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정 시간")
    private LocalDateTime updatedAt;

    public ProductResponseDTO productEntityToDTO(){ // entity to DTO
        return ProductResponseDTO.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .price(price)
                        .stock(stock)
                        .build();
    }
    
    // 재고 업데이트
    public void stockUpdt(Long stock, LocalDateTime updatedAt){
        this.stock = stock;
        this.updatedAt = updatedAt;
    }
}
