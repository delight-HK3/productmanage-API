package com.manage.productmanage.repository.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.model.order.OrderResponseDTO;
import com.manage.productmanage.model.order.OrderResponseDTO.CartList;

@Repository
public interface OrderRepositoryDSL {
    
    // 고객 정보와 일치하는 주문정보 조회
    public List<OrderResponseDTO> findByOrderAndCustomer(Long customerId);

    // 주문목록의 장바구니 정보 조회
    public List<CartList> findByCustomerOrderCart(String orderId);
}
