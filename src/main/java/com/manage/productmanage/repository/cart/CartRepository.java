package com.manage.productmanage.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Cart;

/**
 * 현재 카트에 담겨있는 상품정보를 가져오는 Repository - Spring Data JPA, QueryDSL 활용
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>, CartRepositoryDSL{
    

} 
