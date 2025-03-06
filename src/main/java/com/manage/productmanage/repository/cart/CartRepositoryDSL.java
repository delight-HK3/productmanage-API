package com.manage.productmanage.repository.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Cart;
import com.manage.productmanage.model.cart.CartResponseDTO;

@Repository
public interface CartRepositoryDSL {
    
    // 해당 고객 장바구니에 물건이 존재하는지 조회 (DTO 타입)
    public List<CartResponseDTO> findByCustomerCartDTO(Long customerId);

    // 해당 고객 장바구니에 물건이 존재하는지 조회 (Entity 타입)
    public List<Cart> findByCustomerCartEntity(Long customerId);

    // 요청한 장바구니와 고객정보가 일치하는지 조회
    public Optional<Cart> findByIdAndCustomerId(Long cartId, Long customerId);

    // 장바구니에 요청한 상품이 있는지 조회
    public Optional<Cart> findByCustomerIdAndProductId(Long productId, Long customerId);

}
