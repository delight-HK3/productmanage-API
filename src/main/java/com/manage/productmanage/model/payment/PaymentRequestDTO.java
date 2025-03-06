package com.manage.productmanage.model.payment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 결제 정보 요청 DTO 객체
 */
@Getter
@Setter
public class PaymentRequestDTO {
    
    @NotNull
    private Long customerid;    // 고객 ID (요청)
    @NotEmpty
    private String orderid;     // 주문 코드 (요청)
    @NotNull
    private Long amount;        // 결제 금액 (요청)

}
