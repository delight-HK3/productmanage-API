package com.manage.productmanage.controller.order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manage.productmanage.Enum.ResponseCode;
import com.manage.productmanage.model.api.ApiResponse;
import com.manage.productmanage.model.order.OrderResponseDTO;
import com.manage.productmanage.service.order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 주문 관련기능 Controller
 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    /**
     * 장바구니에 있는 물건들 주문 생성
     * 
     * @param customerId
     * @return ApiResponse<String>
     */
    @PostMapping("/order")
    public ResponseEntity<ApiResponse<String>> order(@RequestParam(value = "customerid") Long customerId){

        List<String> result = orderService.saveOrders(customerId);
        String resultMessage = (result.isEmpty()) ? null : "재고량보다 많은 물품은 주문이 취소 됩니다. : " + result;

        return ApiResponse.success(ResponseCode.SUCCESS_POST.getStatus()
                                    , ResponseCode.SUCCESS_POST.getCode()
                                    , resultMessage
                                    , ResponseCode.SUCCESS_POST.getMessage());
    }

    /**
     * 입력 받은 고객정보와 동일한 주문정보 조회
     * 
     * @param customerId
     * @return ApiResponse<List<OrderResponseDTO>>
     */
    @GetMapping("/order/{customerid}")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrder(@PathVariable(value = "customerid") Long customerId) {
        
        return ApiResponse.success(ResponseCode.SUCCESS_GET.getStatus()
                                    , ResponseCode.SUCCESS_GET.getCode()
                                    , orderService.getOrders(customerId)
                                    , ResponseCode.SUCCESS_GET.getMessage());

    }
    
}
