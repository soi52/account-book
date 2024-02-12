package com.finance.budget.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 스프링부트 서버에 http://localhost:3000 오리진의 요청에 대하여 CORS를 허용
        // WebMvcConfigurer를 implements한 WebMvcConfig 클래스를 생성하여 해당 오리진에 대한 CORS를 허용하여 문제 해결
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE");
    }
}
