package com.manage.productmanage.repository.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Product;

@Repository
public interface ProductRepositoryDSL {

    // 재고가 0개 이상인 상품 조회
    List<Product> findByProductHaveList(); 

    // 선택한 상품의 재고가 충분한지 조회
    Optional<Product> findByProductStock(Long productid, Long stock); 

} 
