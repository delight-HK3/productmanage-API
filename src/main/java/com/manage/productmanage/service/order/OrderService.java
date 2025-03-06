package com.manage.productmanage.service.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.manage.productmanage.Enum.ExceptionCode;
import com.manage.productmanage.exception.NoEnoughException;
import com.manage.productmanage.exception.NoSearchException;

import com.manage.productmanage.domain.Cart;
import com.manage.productmanage.domain.Customer;
import com.manage.productmanage.domain.Orders;
import com.manage.productmanage.domain.Product;
import com.manage.productmanage.model.order.OrderResponseDTO;
import com.manage.productmanage.model.order.OrderResponseDTO.CartList;
import com.manage.productmanage.repository.cart.CartRepository;
import com.manage.productmanage.repository.customer.CustomerRepository;
import com.manage.productmanage.repository.order.OrderRepository;
import com.manage.productmanage.repository.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // OrderService 생성자 주입
public class OrderService {
    
    private final CustomerRepository customerRepository;    // 고객 Repository
    private final CartRepository cartRepository;            // 장바구니 Repository
    private final OrderRepository orderRepository;          // 주문 Repository
    private final ProductRepository productRepository;      // 상품 Repository

    /**
     * 장바구니의 내용 주문목록에 생성
     * 
     * @param customerId
     * @return
     */
    public List<String> saveOrders(Long customerId){

        // 리스트를 등록한 이유는 다른 기능에 비해 주문기능은 
        // 데이터베이스 요청이 많기에 줄이기 위해 saveAll을 사용하기 위한
        // 목적으로 선언했습니다.
        List<Product> products = new ArrayList<>();
        List<Orders> orders = new ArrayList<>();
        List<Cart> carts = new ArrayList<>();

        // 재고량보다 주문이 많이 들어온 물건 리스트
        List<String> noOrders = new ArrayList<>();
        // 장바구니 일련번호 전체합
        Long cartAll = 0L; 

        // 고객정보 조회 (고객 존재여부)
        Customer customer = customerRepository.findById(customerId) 
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        // 고객 장바구니 조회
        List<Cart> cartList = cartRepository.findByCustomerCartEntity(customer.getId()); 

        if(cartList.isEmpty()){ 
            throw new NoSearchException(ExceptionCode.NO_SEARCH_CART);
        }

        // 장바구니 일련번호 전체 합치기
        for(Cart cart : cartList){
            cartAll += cart.getId();
        }

        for(Cart cart : cartList){
            Product product = productRepository.findById(cart.getProduct().getId())
                                .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_PRODUCT));

            if(cart.getCartstock() > product.getStock()){
                noOrders.add(product.getName()); // 재고량보다 주문이 많이 들어온 물건 추가
            } else {
                // 주문목록 생성
                orders.add(Orders.builder()
                            .cart(cart)
                            .price(product.getPrice() * cart.getCartstock())
                            .orderCode("OD"+cart.getCustomer().getId()+"CA"+cartAll)
                            .customer(customer)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build());
                
                // 장바구니 상태 업데이트
                cart.updtOrderYn(1,LocalDateTime.now());
                carts.add(cart);

                // 상품 재고 업데이트
                product.stockUpdt(product.getStock() - cart.getCartstock(),LocalDateTime.now());
                products.add(product);
            }
        }

        // 장바구니에 있는 모든 상품이 재고보다 많은 경우
        if(cartList.size() == noOrders.size()){
            throw new NoEnoughException(ExceptionCode.OVERFLOW_PRODUCT_STOCK);
        }

        orderRepository.saveAll(orders); // 주문정보 등록
        cartRepository.saveAll(carts); // 장바구니 상태 수정
        productRepository.saveAll(products); // 상품 재고 현황 최신화

        return noOrders;
    }

    /**
     * 고객번호와 동일한 주문목록 조회
     * 
     * @param customerId
     * @return
     */
    public List<OrderResponseDTO> getOrders(Long customerId){

        // 고객정보 조회 (고객 존재여부)
        Customer customer = customerRepository.findById(customerId) 
                           .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        // 고객정보에 포함된 주문코드의 중복제거한 리스트 조회
        List<OrderResponseDTO> orderList = orderRepository.findByOrderAndCustomer(customer.getId());
        
        if(orderList.isEmpty()){ // 주문목록이 없는 경우
            throw new NoSearchException(ExceptionCode.NO_SEARCH_ORDERS);
        }
        
        for(OrderResponseDTO order : orderList){
            // 고객정보에 포함된 주문목록의 장바구니 정보 조회
            List<CartList> cartList = orderRepository.findByCustomerOrderCart(order.getOrderId());
            order.setCartList(cartList);
        }

        return orderList;

    }

}
