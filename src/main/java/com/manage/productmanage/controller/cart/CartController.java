package com.manage.productmanage.controller.cart;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manage.productmanage.Enum.ResponseCode;
import com.manage.productmanage.model.api.MyApiResponse;
import com.manage.productmanage.model.cart.CartResponseDTO;
import com.manage.productmanage.model.cart.CartInstRequestDTO;
import com.manage.productmanage.model.cart.CartUpdtRequestDTO;
import com.manage.productmanage.service.cart.CartService;

import jakarta.validation.Valid;

/**
 * 장바구니 관련기능 Controller
 */
@RestController
@RequestMapping("/api/v1")
public class CartController {
    
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    /**
     * 특정 고객의 장바구니 조회 
     *
     * @param customerId
     * @return MyApiResponse<List<CartResponseDTO>>>
     */
    @GetMapping("/cart/{customerid}")
    public ResponseEntity<MyApiResponse<List<CartResponseDTO>>> cart(@PathVariable(value = "customerid") Long customerId) {

        return MyApiResponse.success(ResponseCode.SUCCESS_GET.getStatus()
                                    , ResponseCode.SUCCESS_GET.getCode()
                                    , cartService.getCart(customerId)
                                    , ResponseCode.SUCCESS_GET.getMessage());

    }

    /**
     * 장바구니에 상품정보 저장 
     *
     * @param <T>
     * @param cartInstDTO
     * @return MyApiResponse<T>
     */
    @PostMapping("/cart")
    public <T> ResponseEntity<MyApiResponse<T>> cartInst(@Valid CartInstRequestDTO cartInstRequestDTO){

        cartService.saveCart(cartInstRequestDTO);

        return MyApiResponse.success(ResponseCode.SUCCESS_POST.getStatus()
                                    , ResponseCode.SUCCESS_POST.getCode()
                                    , ResponseCode.SUCCESS_POST.getMessage());
    }

    /**
     * 장바구니 1건 수정
     *
     * @param <T>
     * @param cartId
     * @param customerId
     * @return MyApiResponse<T>
     */
    @PatchMapping("/cart/{customerid}")
    public <T> ResponseEntity<MyApiResponse<T>> cartUpdt(@PathVariable(value = "customerid") Long customerId
                                                        , CartUpdtRequestDTO cartUpdtRequestDTO){

        cartService.updateCart(customerId, cartUpdtRequestDTO);

        return MyApiResponse.success(ResponseCode.SUCCESS_PATCH.getStatus()
                                    , ResponseCode.SUCCESS_PATCH.getCode()
                                    , ResponseCode.SUCCESS_PATCH.getMessage());
    }

    /**
     * 장바구니 1건 삭제 
     *
     * @param <T>
     * @param cartId
     * @param customerId
     * @return MyApiResponse<T>
     */
    @DeleteMapping("/cart/{customerid}")
    public <T> ResponseEntity<MyApiResponse<T>> cartDel(@PathVariable(value = "customerid") Long customerId
                                                        ,@RequestParam("cartid") Long cartId){

        cartService.deleteCart(cartId, customerId);

        return MyApiResponse.success(ResponseCode.SUCCESS_DELETE.getStatus()
                                    , ResponseCode.SUCCESS_DELETE.getCode()
                                    , ResponseCode.SUCCESS_DELETE.getMessage());
    }

}
