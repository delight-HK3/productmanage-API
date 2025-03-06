package com.manage.productmanage.service.cart;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.manage.productmanage.Enum.ExceptionCode;
import com.manage.productmanage.exception.NoEnoughException;
import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.domain.Cart;
import com.manage.productmanage.domain.Customer;
import com.manage.productmanage.domain.Product;
import com.manage.productmanage.model.cart.CartResponseDTO;
import com.manage.productmanage.model.cart.CartInstRequestDTO;
import com.manage.productmanage.model.cart.CartUpdtRequestDTO;
import com.manage.productmanage.repository.cart.CartRepository;
import com.manage.productmanage.repository.customer.CustomerRepository;
import com.manage.productmanage.repository.product.ProductRepository;

import lombok.RequiredArgsConstructor;

/**
 * 장바구니 관련 service 클래스
 */
@Service
@RequiredArgsConstructor // CartService 생성자 주입
public class CartService {
    
    private final CartRepository cartRepository;            // 장바구니 Repository
    private final ProductRepository productRepository;      // 상품 Repository
    private final CustomerRepository customerRepository;    // 고객 Repository

    /**
     * 해당 고객이 가지고 있는 장바구니 목록 조회
     * 
     * @param customerId
     * @return
     */
    public List<CartResponseDTO> getCart(Long customerId){
        
        // 고객정보 조회 (고객 존재여부 확인)
        Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));
        
        // 유저정보와 일치하는 장바구니 정보 조회
        List<CartResponseDTO> customerCart = cartRepository.findByCustomerCartDTO(customer.getId());
        if(customerCart.isEmpty()){
            throw new NoSearchException(ExceptionCode.NO_SEARCH_CART);
        }                                            

        return customerCart;
    }

    /**
     * 고객이 선택한 상품을 장바구니에 등록 / 기존 장바구니에 상품이 있는경우 상품 1개 추가한다.
     * 
     * @param cartInstDTO
     */
    public void saveCart(CartInstRequestDTO cartInstRequestDTO){

        // 고객정보 조회 (고객 존재여부 확인)
        Customer customer = customerRepository.findById(cartInstRequestDTO.getCustomerid())
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        // 선택한 상품의 재고가 충분한지 조회
        Product product = productRepository.findByProductStock(cartInstRequestDTO.getProductid(), cartInstRequestDTO.getStock())
                            .orElseThrow(() -> new NoEnoughException(ExceptionCode.NO_ENOUGH_STOCK)); 

        // 장바구니에 요청한 상품이 있는지 조회
        Cart cart = cartRepository.findByCustomerIdAndProductId(cartInstRequestDTO.getProductid(), cartInstRequestDTO.getCustomerid()).orElse(null);

        if(cart == null){ // 새로운 상품인 경우 장바구니에 추가
            cartRepository.save(new Cart(product, customer, cartInstRequestDTO.getStock(), LocalDateTime.now(), LocalDateTime.now())); 
        } else { // 기존에 장바구니에 있는 상품이면 기존개수에 1개를 추가한다.
            cart.updtCart(cart.getCartstock() + 1, LocalDateTime.now());
            cartRepository.save(cart);
        }
    }

    /**
     * 장바구니 재고량 수정
     *
     * @param cartId
     * @param cartUpdtDTO
     */
    public void updateCart(Long customerId, CartUpdtRequestDTO cartUpdtRequestDTO){

        // 고객정보 조회 (고객 존재여부 확인)
        Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        // 전달받은 고객ID와 장바 구니ID가 일치하는 장바구니 조회
        Cart cart = cartRepository.findByIdAndCustomerId(cartUpdtRequestDTO.getCartid(), customer.getId())
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_CART_HAVE_PRODUCT));
        
        // 선택한 상품의 재고가 충분한지 조회
        productRepository.findByProductStock(cart.getCustomer().getId(), cartUpdtRequestDTO.getStock())
                            .orElseThrow(() -> new NoEnoughException(ExceptionCode.NO_ENOUGH_STOCK)); 
        
        cart.updtCart(cartUpdtRequestDTO.getStock(), LocalDateTime.now()); // 새로운 재고 수량 등록
        
        cartRepository.save(cart); // 조회된 장바구니 내용 수정
    }

    /**
     * 장바구니 삭제
     * 
     * @param cartId
     * @param customerId
     */
    public void deleteCart(Long cartId, Long customerId){

         // 고객정보 조회 (고객 존재여부 확인)
         Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        // 전달받은 고객ID와 장바구니ID와 같은 장바구니 조회 
        Cart cart = cartRepository.findByIdAndCustomerId(cartId, customer.getId())
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_CART_HAVE_PRODUCT));
        
        cartRepository.delete(cart); // 조회된 장바구니 삭제
    }
    
}
