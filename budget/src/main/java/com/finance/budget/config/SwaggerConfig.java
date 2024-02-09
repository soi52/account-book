package com.finance.budget.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Account Book (가계부)",
                description = "Account-Book API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
    // swagger 주소 : http://localhost:8080/swagger-ui/index.html
}
