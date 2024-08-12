package br.com.spring_boot_expert.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sales API")
                        .description("Sales project API.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Paulo Vitor Costa Silva")
                                .email("paulo.vitorcs@hotmail.com")
                                .url("https://github.com/Paulo-vitorCS"))

                ) // Autorização de token via swagger
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springboot")
                .pathsToMatch("/**")
                .packagesToScan("br.com.spring_boot_expert.rest.controller")
                .build();
    }

}
