package com.indtexbr.processoindustrial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author lgdamy@raiadrogasil.com on 13/03/2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Gest\u00e3o de Processo Industrial", "Microsservi\u00e7o utilit\u00e1rio para configura\u00e7\u00e3o do m\u00f3dulo de processo industrial", "1.0", "urn:tos",
                        new Contact("Adamastor Ten\u00f3rio","","adamastor@indtexbr.com.br"), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>()))
                .tags(new Tag("Licita\u00e7\u00e3o", "Acesso \u00e0s licita\u00e7\u00f5es"),
                        new Tag("Or\u00e7amento", "Acesso aos Or\u00e7amentos relacionados \u00e0s licita\u00e7\u00f5es"))
                .consumes(Set.of(MediaType.APPLICATION_JSON_VALUE))
                .produces(Set.of(MediaType.APPLICATION_JSON_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.indtexbr"))
                .paths(PathSelectors.any()).build()
                .pathMapping("/")
                .securityContexts(Collections.singletonList(SecurityContext.builder().securityReferences(globalAuth()).build()));

    }

    private List<SecurityReference> globalAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("read", "Grants read access");
        authorizationScopes[1] = new AuthorizationScope("write", "Grants write access");
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
