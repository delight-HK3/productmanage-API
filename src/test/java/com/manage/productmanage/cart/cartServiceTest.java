package com.manage.productmanage.cart;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manage.productmanage.exception.NoEnoughException;
import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.model.cart.CartResponseDTO;
import com.manage.productmanage.model.cart.CartInstRequestDTO;
import com.manage.productmanage.model.cart.CartUpdtRequestDTO;
import com.manage.productmanage.service.cart.CartService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("장바구니 관련기능 테스트")
public class cartServiceTest {
    
    @Autowired
    private CartService cartService;

    @Test
    @DisplayName("해당 유저의 장바구니 리스트 조회 테스트")
	public void getCartTest(){
        // 성공 : 해당 고객의 장바구니 정보 출력
        // 실패 : exceiption의 메세지 로그 출력
        try{
            List<CartResponseDTO> allList = cartService.getCart(1L);
            log.info(allList.toString());
        } catch(NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다.
            // 메세지 2 : 장바구니에 아무것도 없습니다.
        }
    }

    @Test
    @DisplayName("장바구니에 상품 입력 테스트")
	public void saveCartTest(){
        // 성공 : "장바구니에 등록 성공" 출력
        // 실패 : exceiption의 메세지 로그 출력
        try{
            CartInstRequestDTO cartInstRequestDTO = new CartInstRequestDTO();
            cartInstRequestDTO.setProductid(2L);   // 상품번호
            cartInstRequestDTO.setCustomerid(3L);  // 고객번호
            cartInstRequestDTO.setStock(5L);      // 상품개수

            cartService.saveCart(cartInstRequestDTO);
            log.info("장바구니에 등록 성공");
        } catch (NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다.
            // 메세지 2 : 존재하지 않는 상품 입니다.
        } catch (NoEnoughException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 재고가 충분하지 않습니다.
        }

    }

    @Test
    @DisplayName("장바구니의 특정 상품 수정 테스트")
    public void updtCartTest(){
        // 성공 : "장바구니 상품 수정 성공" 출력
        // 실패 : exceiption의 메세지 로그 출력
        try{
            CartUpdtRequestDTO cartUpdtRequestDTO = new CartUpdtRequestDTO();
            cartUpdtRequestDTO.setCartid(1L);
            cartUpdtRequestDTO.setStock(5L);

            cartService.updateCart(1L, cartUpdtRequestDTO);
            log.info("장바구니 상품 수정 성공");
        } catch (NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다.
            // 메세지 2 : 장바구니에 해당 상품이 존재하지 않습니다.
        }

    }

    @Test
    @DisplayName("장바구니의 특정 상품 삭제 테스트")
    public void deleteCartTest(){
        // 성공 : "장바구니 상품 삭제 성공" 출력
        // 실패 : exceiption의 메세지 출력
        try{
            cartService.deleteCart(6L, 1L);
            log.info("장바구니 상품 삭제 성공");
        } catch (NoSearchException e){
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다.
            // 메세지 2 : 장바구니에 해당 상품이 존재하지 않습니다.
        }
        
    }
}
