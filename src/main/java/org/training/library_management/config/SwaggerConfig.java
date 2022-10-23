package org.training.library_management.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    private static final AuthorizationScope[] SCOPES = { new AuthorizationScope("global", "accessEverything") };

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(getSecurityContexts())
                .securitySchemes(Arrays.asList(getApiKey()))
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

    private ApiKey getApiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private List<SecurityContext> getSecurityContexts() {
        return List.of(SecurityContext.builder().securityReferences(getSecurityReferences()).build());
    }

    private List<SecurityReference> getSecurityReferences() {
        return List.of(new SecurityReference("JWT", SCOPES));
    }
}
