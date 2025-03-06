package com.manage.productmanage.repository.payment;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.model.payment.PaymentResponseDTO;

@Repository
public interface PaymentRepositoryDSL {

    // 해당하는 유저의 결재내역 조회
    List<PaymentResponseDTO> findByPaymentCustomer(Long customerId);
    
}