package org.training.library_management.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.training.library_management.controllers"))
                .build()
                .apiInfo(getInfo());

    }

    private ApiInfo getInfo() {
        return new ApiInfo("Library management API",
                "Library Management System developed by Rakshith",
                "1.0", "strictly for study",
                new Contact("Rakshith R", "https://github.com/Rakshi-72", "rakshithjoghalli@gmail.com"),
                "under swagger API license",
                "https://swagger.io/license/",
                Collections.emptyList());
    }
}
