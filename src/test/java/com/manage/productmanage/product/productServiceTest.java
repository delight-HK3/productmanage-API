package com.manage.productmanage.product;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manage.productmanage.exception.NoSearchException;
import com.manage.productmanage.model.product.ProductResponseDTO;
import com.manage.productmanage.service.product.ProductService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("상품 관련기능 테스트")
public class productServiceTest {   

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("전체 상품 리스트 가져오기 테스트")
	public void getProductAllListTest(){
        // 성공 : 전체 상품리스트 출력
        // 실패 : exceiption의 메세지 출력
        try{
            List<ProductResponseDTO> allList = productService.getProductAllList();
            log.info(allList.toString());
        } catch(NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 : 존재하지 않는 상품 입니다.
        }
    }

    @Test
    @DisplayName("상품개수 0개 이상 상품 리스트 가져오기 테스트")
	public void getProductHaveListTest(){
        // 성공 : stock이 0이상인 상품리스트 출력
        // 실패 : exceiption의 메세지 출력
        try {
            List<ProductResponseDTO> allHaveList = productService.getProductHaveList();
            log.info(allHaveList.toString());
        } catch (NoSearchException e){
            log.warn(e.getMessage());
            // 메세지 : 현재 구매 가능한 상품이 없습니다.
        }
    }

    @Test
    @DisplayName("특정 ID상품 조회 테스트")
	public void productIdTest(){
        // 성공 : 번호와 일치하는 상품번호 조회
        // 실패 : exceiption의 메세지 출력
        try {
            ProductResponseDTO oneData = productService.getProductOne(1L);
            log.info(oneData.toString());
        } catch (NoSearchException e){ 
            log.warn(e.getMessage());
            // 메세지 : 존재하지 않는 상품 입니다.
        }
    }
}
