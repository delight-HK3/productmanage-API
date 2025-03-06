package com.manage.productmanage.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Payment;

/**
 * 결재 및 결재정보를 조회하는 Repository - Spring Data JPA, QueryDSL 활용
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryDSL{
    

}
