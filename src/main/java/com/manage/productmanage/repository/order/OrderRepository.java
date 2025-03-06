package com.manage.productmanage.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Orders;

 /**
 * 주문 및 주문문정보를 조회하는 Repository - Spring Data JPA, QueryDSL 활용
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>, OrderRepositoryDSL{
    
    // 장바구니에서 주문번호와 고객번호가 같은 장바구니 가격의 총합 조회
    @Query(value="SELECT * FROM orders WHERE order_code = :orderId AND customer_id = :customerId", nativeQuery= true)
    List<Orders> findByOrderIdAndCustomerId(@Param(value = "orderId") String orderId
                                            , @Param(value = "customerId") Long customerId);

}
