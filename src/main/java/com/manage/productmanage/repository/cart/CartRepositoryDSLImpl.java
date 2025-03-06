package com.manage.productmanage.repository.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import static com.manage.productmanage.domain.QCart.cart; 

import com.manage.productmanage.domain.Cart;
import com.manage.productmanage.model.cart.CartResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CartRepositoryDSLImpl implements CartRepositoryDSL{

    private final JPAQueryFactory queryFactory;

    public CartRepositoryDSLImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    /**
     * 해당 고객 장바구니에 물건이 존재하는지 리스트 조회 (DTO 타입)
     */
    @Override
    public List<CartResponseDTO> findByCustomerCartDTO(Long customerId) {
        List<CartResponseDTO> cartDTO = queryFactory
                            .select(Projections.constructor(CartResponseDTO.class,
                                cart.id
                                , cart.customer.name
                                , cart.product.name
                                , cart.product.price
                                , cart.cartstock
                                , Expressions.asNumber(cart.product.price.multiply(cart.cartstock))
                            )).from(cart)
                            .where(cart.customer.id.eq(customerId) // 고객번호
                                    , cart.orderYn.eq(0))    // 주문여부 (0: 주문 대기)
                            .fetch();
        return cartDTO;
    }

    /**
     * 해당 고객 장바구니에 물건이 존재하는지 리스트 조회 (Entity 타입)
     */
    @Override
    public List<Cart> findByCustomerCartEntity(Long customerId) {
        List<Cart> cartEntiry = queryFactory
                                    .selectFrom(cart)
                                    .where(cart.customer.id.eq(customerId) // 고객번호
                                            , cart.orderYn.eq(0))    // 주문여부 (0: 주문 대기)
                                    .fetch();
        
        return cartEntiry;
    }
    /**
     * 요청한 장바구니와 고객정보가 존재하는지 단일건 조회 
     */
    @Override
    public Optional<Cart> findByIdAndCustomerId(Long cartId, Long customerId) {
        Optional<Cart> cartResult = 
                    Optional.ofNullable(queryFactory
                                        .selectFrom(cart)
                                        .where(cart.id.eq(cartId)             // 장바구니 번호
                                            , cart.customer.id.eq(customerId) // 고객번호
                                            , cart.orderYn.eq(0))       // 주문여부 (0: 주문 대기)
                                        .fetchOne());
                                
        return cartResult;           
    }

    /**
     * 장바구니에 요청한 상품이 있는지 조회
     */
    @Override
    public Optional<Cart> findByCustomerIdAndProductId(Long productId, Long customerId) {
        Optional<Cart> cartResult = 
                    Optional.ofNullable(queryFactory
                                        .selectFrom(cart)
                                        .where(cart.customer.id.eq(customerId)
                                            , cart.product.id.eq(productId)   // 고객번호
                                            , cart.orderYn.eq(0))       // 주문여부 (0: 주문 대기)
                                        .fetchOne());
                                
        return cartResult;
    }
    
}
