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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * orders(주문목록) Entity
 */
@Entity
@Getter
@Table(name="orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "주문 ID (고유 키)")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @Comment(value = "장바구니 물품 ID")
    private Cart cart;

    @Column(name = "price", nullable = false)
    @Comment(value = "장바구니 물품 가격")
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @Comment(value = "고객 ID (외래 키)")
    private Customer customer;

    @Column(name = "orderCode", nullable = false)
    @Comment(value = "주문번호")
    private String orderCode;

    @Column(name = "paymentYn")
    @ColumnDefault(value = "0")
    @Comment(value = "결제 여부 (0: 결제 대기 / 1: 결제 완료)")
    private int paymentYn;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정 시간")
    private LocalDateTime updatedAt;

    @Builder
    public Orders(Cart cart, Long price, Customer customer, String orderCode
                    , LocalDateTime createdAt, LocalDateTime updatedAt){
        this.cart = cart;
        this.price = price;
        this.customer = customer;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 결재여부 업데이트
    public void paymentYnUpdt(int paymentYn){
        this.paymentYn = paymentYn;
    }

}
