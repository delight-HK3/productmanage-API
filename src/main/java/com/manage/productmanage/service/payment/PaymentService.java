package com.manage.productmanage.service.payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.manage.productmanage.Enum.ExceptionCode;
import com.manage.productmanage.exception.NoEnoughException;
import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.domain.Customer;
import com.manage.productmanage.domain.Orders;
import com.manage.productmanage.domain.Payment;
import com.manage.productmanage.model.payment.PaymentRequestDTO;
import com.manage.productmanage.model.payment.PaymentResponseDTO;
import com.manage.productmanage.repository.customer.CustomerRepository;
import com.manage.productmanage.repository.order.OrderRepository;
import com.manage.productmanage.repository.payment.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository paymentRepository;      // 결재 Repository
    private final CustomerRepository customerRepository;    // 고객 Repository
    private final OrderRepository orderRepository;          // 주문 Repository

    @Value("${api.beeceptor.payment}")
    private String API_URL;

    /**
     * 결제 API요청을 통해 결제진행
     * 
     * @param customerId
     * @param paymentRequestDTO
     */
    public void payment(PaymentRequestDTO paymentRequestDTO){

        // 고객정보 조회 (유저 존재여부)
        Customer customer = customerRepository.findById(paymentRequestDTO.getCustomerid())
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        // 입력한 주문번호가 존재하는지 조회
        List<Orders> orders = orderRepository.findByOrderIdAndCustomerId(paymentRequestDTO.getOrderid(), customer.getId())
                            .stream().collect(Collectors.toList());
        
        // 유효성 검사
        this.validationCheck(customer, orders ,paymentRequestDTO.getOrderid() , paymentRequestDTO.getAmount());

        // 외부 결제 API 호출
        RestClient restClient = RestClient.builder()
                        .baseUrl(API_URL)
                        .build();
        
        ResponseEntity<String> response = restClient.post()
                    .uri("/api/v1/payment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(paymentRequestDTO)
                    .retrieve()
                    .toEntity(String.class);
        
        // 결제 성공
        if(response.getStatusCode().is2xxSuccessful()){
            log.info(response.getBody());
            Payment successPay = Payment.builder()
                                    .orderCode(paymentRequestDTO.getOrderid())
                                    .successYn(1)
                                    .customer(customer)
                                    .amount(paymentRequestDTO.getAmount())
                                    .createdAt(LocalDateTime.now())    
                                    .updatedAt(LocalDateTime.now())
                                    .build();

            paymentRepository.save(successPay);

            for(Orders order : orders){ // 주문목록 결제완료 처리
                order.paymentYnUpdt(1);
                orderRepository.save(order);
            }

        } else { // 결제 실패
            log.warn(response.getBody());

            Payment failPay = Payment.builder()
                                .orderCode(paymentRequestDTO.getOrderid())
                                .successYn(0)
                                .customer(customer)
                                .amount(paymentRequestDTO.getAmount())
                                .failReason("결제 API 오류 및 접속문제로 인해 결제가 취소 되었습니다.")
                                .createdAt(LocalDateTime.now())    
                                .updatedAt(LocalDateTime.now())
                                .build();
            
            paymentRepository.save(failPay);
        }

    }

    /**
     * 유효성 검사 메서드
     * 
     * @param customer
     * @param orders
     * @param orderId
     * @param amount
     */
    private void validationCheck(Customer customer, List<Orders> orders, String orderId, Long amount){
        // 주문번호가 일치하지 않는 경우
        if(orders.isEmpty()){
            throw new NoSearchException(ExceptionCode.NO_MATCH_ORDERID);
        }  
        
        // 주문금액의 총합
        Long priceAll = 0L; 
             
        for(Orders order : orders){ // 주문한 물건의 가격 총합
            if(order.getPaymentYn() == 1){ // 이미 결제가 된 주문
                throw new NoSearchException(ExceptionCode.ALEADY_PAYMENT_ORDERID);
            }
            priceAll += order.getPrice();
        }

        // 지불한 금액이 충분한지 검사
        if(priceAll > amount){

            // 결제 실패 로그 추가
            Payment failPay = Payment.builder()
                                .orderCode(orderId)
                                .successYn(0)
                                .amount(amount)
                                .customer(customer)
                                .failReason(ExceptionCode.NO_ENOUGH_AMOUNT.getMessage())
                                .createdAt(LocalDateTime.now())    
                                .updatedAt(LocalDateTime.now())
                                .build();
            
            paymentRepository.save(failPay); 

            throw new NoEnoughException(ExceptionCode.NO_ENOUGH_AMOUNT);
        }
    }

    /**
     * 해당하는 고객의 결제내역 조회
     * 
     * @param customerId
     * @return
     */
    public List<PaymentResponseDTO> getPayment(Long customerId){

        // 고객정보 조회 (유저 존재여부)
        Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_CUSTOMER));

        List<PaymentResponseDTO> allResult = paymentRepository.findByPaymentCustomer(customer.getId());

        if(allResult.isEmpty()){
            throw new NoSearchException(ExceptionCode.NO_SEARCH_PAYMENT);
        }

        return allResult;

    }
}
