package com.manage.productmanage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * PUT, PATCH, DELETE메서드가 동작하지 않아 CORS문제로 인한문제로 예상하여 Config파일 추가
 */
@Configuration
public class ApiConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**") // 모든경로에 해당 설정을 적용
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // 허용할 메서드
                .allowedHeaders("*"); // 허용할 Header 목록
    }
}
