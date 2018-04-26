package com.threem.carrental.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author marek_j on 2018-04-17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.threem.carrental.controller"))
                .paths(PathSelectors.regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("car-rental-app")
                .description("Academic Project of the ThreeM Drink Team")
                .version("0.0.1")
                .contact(new Contact("ThreeM","https://github.com/threem-project",""))
                .build();
    }

}
