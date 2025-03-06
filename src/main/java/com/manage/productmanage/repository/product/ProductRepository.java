package com.manage.productmanage.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Product;

/**
 * 상품정보를 가져오는 Repository - Spring Data JPA활용
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryDSL{


} 
