package org.training.library_management.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();

    }

    private ApiInfo getInfo() {
        return new ApiInfo("Library management API",
                "Library Management System developed by Rakshith",
                "1.0", "strictly for study",
                "+91 9739198164",
                "under swagger",
                "no link");
    }
}
