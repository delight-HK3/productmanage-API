package com.manage.productmanage.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manage.productmanage.Enum.ExceptionCode;
import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.domain.Product;
import com.manage.productmanage.model.product.ProductResponseDTO;
import com.manage.productmanage.repository.product.ProductRepository;

/**
 * 상품정보 관련 service 클래스
 */
@Service
public class ProductService {
    
    private final ProductRepository productRepository; // 상품 Repository

    // productService 생성자 주입
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    
    /**
     * 상품 전체 내용 조회
     * 
     * @return List<ProductDTO>
     */
    public List<ProductResponseDTO> getProductAllList(){
        List<ProductResponseDTO> allresult = productRepository.findAll()
                                                    .stream()
                                                    .map(Product::productEntityToDTO)
                                                    .collect(Collectors.toList());
        
        if(allresult.isEmpty()){
            throw new NoSearchException(ExceptionCode.NO_PRODUCT_ALL_LIST);
        }

        return allresult;
    }

    /**
     * 특정 ID 상품 조회
     * 
     * @param id
     * @return ProductDTO
     */
    public ProductResponseDTO getProductOne(Long productid){
        return productRepository.findById(productid)
                                .map(Product::productEntityToDTO)
                                .orElseThrow(() -> new NoSearchException(ExceptionCode.NO_SEARCH_PRODUCT)); 
    }

    /**
     * 재고가 남아있는 상품 즉 현재 구매가능한 상품 조회
     * 
     * @return List<ProductDTO>
     */
    public List<ProductResponseDTO> getProductHaveList(){

        List<ProductResponseDTO> haveresult = productRepository.findByProductHaveList()
                                                    .stream()
                                                    .map(Product::productEntityToDTO)
                                                    .collect(Collectors.toList());

        if(haveresult.isEmpty()){
            throw new NoSearchException(ExceptionCode.NO_PRODUCT_HAVE_LIST);
        }

        return haveresult;
    }
}
