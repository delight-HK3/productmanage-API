package com.manage.productmanage.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * cart(장바구니) Entity
 */
@Entity
@Getter
@Table(name="cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "장바구니 ID (고유 키)")
    private Long id;              

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Comment(value = "상품 ID(외래 키)")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @Comment(value = "고객 ID(외래 키)")
    private Customer customer;

    @Column(name = "cartstock", nullable = false)
    @Comment(value = "등록한 물건 수량")
    private Long cartstock;

    @Column(name = "orderYn")
    @ColumnDefault(value = "0")
    @Comment(value = "주문 여부 (0: 주문 대기 / 1: 주문 완료)")
    private int orderYn;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정 시간")
    private LocalDateTime updatedAt;

    public Cart(Product product,Customer customer,Long cartstock, 
                    LocalDateTime createdAt, LocalDateTime updatedAt){
        this.product = product;
        this.customer = customer;
        this.cartstock = cartstock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 장바구니 제품수량 업데이트
    public void updtCart(Long cartStock, LocalDateTime updatedAt){
        this.cartstock = cartStock;
        this.updatedAt = updatedAt;
    }

    // 장바구니 상태 업데이트
    public void updtOrderYn(int orderYn, LocalDateTime updatedAt){
        this.orderYn = orderYn;
        this.updatedAt = updatedAt;
    }
}
