package com.manage.productmanage.order;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manage.productmanage.model.order.OrderResponseDTO;
import com.manage.productmanage.service.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("주문 관련기능 테스트")
public class orderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("장바구니의 물건을 주문목록에 저장 테스트")
	public void saveOrdersTest(){
        // 성공 : 1. 장바구니에서 1번 고객의 orderYn의 값이 1로 변경
        //        2. 주문목록에 1번 고객의 주문내역 생성
        //        3. 상품 재고에 변화가 있으면 성공
        // 실패 : exceiption의 메세지 출력
        try {
            orderService.saveOrders(3L);    
        } catch (Exception e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다. 
            // 메세지 2 : 장바구니에 아무것도 없습니다.
            // 메세지 3 : 존재하지 않는 상품 입니다.
        }

    }

    @Test
    @DisplayName("주문목록 조회 테스트")
    public void getOrdersTest(){
        // 성공 : 고객번호와 같은 주문번호를 장바구니 내용과 함께 조회
        // 실패 : exceiption의 메세지 출력
        try {
            List<OrderResponseDTO> orderList = orderService.getOrders(3L); 
            log.info(orderList.toString());   
        } catch (Exception e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다. 
        }
    }
}
