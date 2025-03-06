package com.manage.productmanage.controller.payment;

import org.springframework.web.bind.annotation.RestController;

import com.manage.productmanage.Enum.ResponseCode;
import com.manage.productmanage.model.api.ApiResponse;
import com.manage.productmanage.model.payment.PaymentRequestDTO;
import com.manage.productmanage.model.payment.PaymentResponseDTO;
import com.manage.productmanage.service.payment.PaymentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 결제 관련기능 Controller
 */
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /**
     * 주문테이블에 있는 주문목록 결제처리
     * 
     * @param <T>
     * @param customerId
     * @param orderId
     * @param amount
     * @return ApiResponse<T>
     */
    @PostMapping("/payment")
    public <T> ResponseEntity<ApiResponse<T>> payment(@Valid PaymentRequestDTO paymentRequestDTO) {
        
        paymentService.payment(paymentRequestDTO);

        return ApiResponse.success(ResponseCode.SUCCESS_POST.getStatus()
                                    , ResponseCode.SUCCESS_POST.getCode()
                                    , ResponseCode.SUCCESS_POST.getMessage());

    }

    /**
     * 해당하는 고객의 결제내역 조회
     * 
     * @param <T>
     * @param customerId
     * @return ApiResponse<PaymentResponseDTO>
     */
    @GetMapping("/payment/{customerid}")
    public ResponseEntity<ApiResponse<List<PaymentResponseDTO>>> getPayment(
                                        @PathVariable(value = "customerid") Long customerId) {

        return ApiResponse.success(ResponseCode.SUCCESS_GET.getStatus()
                                    , ResponseCode.SUCCESS_GET.getCode()
                                    , paymentService.getPayment(customerId)
                                    , ResponseCode.SUCCESS_GET.getMessage());

    }
    
}
