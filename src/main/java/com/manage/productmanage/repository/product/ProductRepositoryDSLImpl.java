package com.manage.productmanage.repository.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Product;
import static com.manage.productmanage.domain.QProduct.product;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ProductRepositoryDSLImpl implements ProductRepositoryDSL{

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryDSLImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    /**
     * 재고가 0개 이상인 상품 조회
     */
    @Override
    public List<Product> findByProductHaveList() {
        List<Product> productList = queryFactory
                                        .selectFrom(product)
                                        .where(product.stock.gt(0))
                                        .fetch();
        
        return productList;
    }

    /**
     * 선택한 상품의 재고가 충분한지 조회
     */
    @Override
    public Optional<Product> findByProductStock(Long productid, Long stock) {
        Optional<Product> productResult = 
                        Optional.ofNullable(queryFactory
                                            .selectFrom(product)
                                            .where(product.id.eq(productid)
                                                , product.stock.goe(stock))
                                            .fetchOne());
        
        return productResult;
    }
    
}
