package com.manage.productmanage.payment;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manage.productmanage.exception.NoEnoughException;
import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.model.payment.PaymentRequestDTO;
import com.manage.productmanage.model.payment.PaymentResponseDTO;
import com.manage.productmanage.service.payment.PaymentService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("결제 관련기능 테스트")
public class paymentServiceTest {
    
    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("주문목록 결제 테스트")
	public void paymentTest(){
        // 성공 : 1. 주문목록에 결재여부 컬럼이 0에서 1로 변경
        //        2. 결제성공 : 결제로그 성공 데이터 추가
        //           결제실패 : 결제로그 실패 데이터 추가
        // 실패 : exceiption의 메세지 출력
        try{
            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
            paymentRequestDTO.setAmount(200000L);
            paymentRequestDTO.setOrderid("OD3CA6");

            paymentService.payment(paymentRequestDTO);
        } catch(NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다.
            // 메세지 2 : 주문번호와 일치하는 주문이 없습니다.
            // 메세지 3 : 이미 결제가 된 주문 입니다.
        } catch(NoEnoughException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 금액이 충분하지 않습니다.
        }
    }

    @Test
    @DisplayName("주문목록 조회 테스트")
	public void getPaymentTest(){
        // 성공 : 입력한 번호의 고객의 결제목록 조회
        // 실패 : exceiption의 메세지 출력
        try{
            List<PaymentResponseDTO> paymentList = paymentService.getPayment(3L);
            log.info(paymentList.toString());
        } catch (NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 고객 입니다.
            // 메세지 2 : 결재목록이 존재하지 않습니다.
        }
    }

}   
