package com.manage.productmanage.controller.product;

import org.springframework.web.bind.annotation.RestController;

import com.manage.productmanage.Enum.ResponseCode;
import com.manage.productmanage.model.api.ApiResponse;
import com.manage.productmanage.model.product.ProductResponseDTO;
import com.manage.productmanage.service.product.ProductService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 상품 관련기능 Controller
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
     * 재고가 0개 이상인 판매 가능 상품 목록 조회
     * 
     * @return ApiResponse<List<ProductResponseDTO>>
     */
    @GetMapping("/product")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> product() {
        
        return ApiResponse.success(ResponseCode.SUCCESS_GET.getStatus()
                                    , ResponseCode.SUCCESS_GET.getCode()
                                    , productService.getProductHaveList()
                                    , ResponseCode.SUCCESS_GET.getMessage());           
    }
    
    /**
     * 특정 ID의 상품 조회
     * 
     * @param id
     * @return ApiResponse<ProductResponseDTO>
     */
    @GetMapping("/product/{productid}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> productId(@PathVariable(value = "productid") Long productid) {

        return ApiResponse.success(ResponseCode.SUCCESS_GET.getStatus()
                                    , ResponseCode.SUCCESS_GET.getCode()
                                    , productService.getProductOne(productid)
                                    , ResponseCode.SUCCESS_GET.getMessage());
    }

    /**
     * 재고량 관계없이 상품 전체 목록 조회
     * 
     * @return ApiResponse<List<ProductResponseDTO>>
     */
    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> productAll() {

        return ApiResponse.success(ResponseCode.SUCCESS_GET.getStatus()
                                    , ResponseCode.SUCCESS_GET.getCode()
                                    , productService.getProductAllList()
                                    , ResponseCode.SUCCESS_GET.getMessage());
    }
}
