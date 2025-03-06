package com.manage.productmanage.repository.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.model.order.OrderResponseDTO;
import com.manage.productmanage.model.order.OrderResponseDTO.CartList;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.manage.productmanage.domain.QOrders.orders;

@Repository
public class OrderRepositoryDSLImpl implements OrderRepositoryDSL{

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryDSLImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    /**
     * 고객 정보와 일치하는 주문정보 조회
     */
    @Override
    public List<OrderResponseDTO> findByOrderAndCustomer(Long customerId) {
        
        List<OrderResponseDTO> orderList = queryFactory
                                            .select(
                                                Projections.constructor(OrderResponseDTO.class,
                                                orders.orderCode
                                                , orders.customer.name
                                                , orders.paymentYn.when(0).then("결제 대기")
                                                .otherwise("결제 완료") 
                                            )).distinct()
                                            .from(orders)
                                            .where(orders.customer.id.eq(customerId)) // customerId : 고객 번호
                                            .fetch();
        return orderList;                                    
    }
    
    /**
     * 주문목록의 장바구니 정보 조회
     */
    @Override
    public List<CartList> findByCustomerOrderCart(String orderId) {
        List<CartList> cartDTO = queryFactory
                                .select(
                                    Projections.constructor(CartList.class,
                                    orders.cart.id
                                    , orders.cart.product.name
                                    , orders.cart.product.price
                                    , orders.cart.cartstock
                                    , Expressions.asNumber(orders.cart.product.price.multiply(orders.cart.cartstock))
                                )).from(orders)
                                .where(orders.orderCode.eq(orderId)) // orderId : 주문코드
                                .fetch();
        return cartDTO;
    }
}
