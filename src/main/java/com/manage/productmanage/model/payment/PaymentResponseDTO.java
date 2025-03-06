package com.manage.productmanage.model.payment;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

/**
 * 결제 정보 리턴 DTO 객체
 */
@Getter
@ToString
public class PaymentResponseDTO {
    
    private final Long id;                  // 결제 번호
    private final String name;              // 고객 이름
    private final String orderCode;         // 주문 코드
    private final Long amount;              // 결제 금액
    private final String successYn;         // 결제 성공여부
    private final String failReason;        // 결레 실패사유
    private final LocalDateTime createDt;   // 거래 시간

    public PaymentResponseDTO(Long id, String name, String orderCode, Long amount
                            ,String successYn, String failReason, LocalDateTime createDt){
        this.id = id;
        this.name = name;
        this.orderCode = orderCode;
        this.successYn = successYn;
        this.amount = amount;
        this.failReason = failReason;
        this.createDt = createDt;
    }
}
