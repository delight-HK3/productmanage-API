package com.manage.productmanage.repository.payment;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.model.payment.PaymentResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.manage.productmanage.domain.QPayment.payment;

@Repository
public class PaymentRepositoryDSLImpl implements PaymentRepositoryDSL{

    private final JPAQueryFactory queryFactory;

    public PaymentRepositoryDSLImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    /**
     * 해당하는 고객의 결재내역 조회 (DTO 타입)
     */
    @Override
    public List<PaymentResponseDTO> findByPaymentCustomer(Long customerId) {
        
        List<PaymentResponseDTO> paymentDTO = queryFactory
                                                .select(
                                                    Projections.constructor(PaymentResponseDTO.class,
                                                    payment.id
                                                    , payment.customer.name
                                                    , payment.orderCode
                                                    , payment.amount
                                                    , payment.successYn.when(0).then("결제 실패")
                                                    .otherwise("결제 성공")           
                                                    , payment.failReason
                                                    , payment.createdAt
                                                )).from(payment)
                                                .where(payment.customer.id.eq(customerId))  // 고객번호
                                                .orderBy(payment.id.desc())                 // 결제번호 내림차순
                                                .fetch();
        return paymentDTO;
    }
    
}
