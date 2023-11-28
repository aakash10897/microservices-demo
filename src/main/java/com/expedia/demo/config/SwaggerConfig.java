package com.expedia.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.OAuth2Scheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String CONTROLLERS_BASE_PACKAGE = "com.expedia.demo.controller";

    private static final AuthorizationScope[] AUTHORIZATION_SCOPES = {new AuthorizationScope("SCOPE_sem.data.all", "read/write scope"),
            new AuthorizationScope("SCOPE_sem.data.read", "read-only scope")};

    @Value("${server.servlet.context-path}")
    private String deployPath;

    @Bean
    public Docket swaggerMvc() {
        return new Docket(DocumentationType.OAS_30).select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLERS_BASE_PACKAGE))
                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
                .paths(PathSelectors.regex(
                        "/.*"))
                .build()
                .directModelSubstitute(LocalDate.class, String.class).useDefaultResponseMessages(false);
    }

}
