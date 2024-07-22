package com.studycow.config;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

//Swagger-UI 확인
//http://localhost/swagger-ui.html

@Configuration // 스프링 설정 클래스임을 나타냅니다.
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }


    private Info apiInfo() {
        return new Info().title("Study Cow API 문서") // API 제목을 설정합니다.
                .description("<h3>REST API 문서 내용을 다음과 같이 제공합니다.</h3>") // API 설명을 설정합니다.
                .version("1.0.0") // API 버전을 설정합니다.
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }
}
