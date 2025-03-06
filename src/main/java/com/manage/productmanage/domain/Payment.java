package com.manage.productmanage.domain;

import java.time.LocalDateTime;

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
import lombok.Builder;
import lombok.Getter;

/**
 * payment(결제내역) Entity
 */
@Entity
@Getter
@Table(name="payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "결제 ID (고유 키)")
    private Long id;       

    @Column(name = "orderCode", nullable = false)
    @Comment(value = "주문번호")
    private String orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @Comment(value = "고객 ID (외래 키)")
    private Customer customer;

    @Column(name = "successYn", nullable = false)
    @Comment(value = "결제 성공 여부 (0: 결제 실패 / 1: 결제 성공)")
    private int successYn;

    @Column(name = "failReason")
    @Comment(value = "결제 실패사유")
    private String failReason;

    @Column(name = "amount", nullable = false)
    @Comment(value = "지불한 금액")
    private Long amount;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정 시간")
    private LocalDateTime updatedAt;

    @Builder
    public Payment(String orderCode, int successYn,String failReason, Long amount
                    , Customer customer, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.failReason = failReason;
        this.amount = amount;
        this.customer = customer;
        this.orderCode = orderCode;
        this.successYn = successYn;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
