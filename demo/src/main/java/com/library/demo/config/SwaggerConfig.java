package com.library.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //changes
    
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("sleepy-library-api")
                            .apiInfo(apiInfo()).select()
                            .paths(PathSelectors.ant("/api/**"))
                            .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Sleepy Library API")
                                    .description("A REST API with CRUD Operations ")
                                    .license("MIT License")
                                    .licenseUrl("example@gmail.com")
                                    .version("1.0").build();
    }
}
