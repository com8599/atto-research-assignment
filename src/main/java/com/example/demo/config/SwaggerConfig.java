package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI restAPI() {
        Info info = new Info()
                .title("Demo API")
                .version("0.0.1")
                .description("데모데모")
                .termsOfService("")
                .contact(new Contact().name("이지영").url("https://tuliplee.tk").email("com8599@gmail.com"));

        List<Server> servers = List.of(new Server().url("http://localhost:8080").description(""));

        SecurityScheme basicAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityItem = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", basicAuth))
                .addSecurityItem(securityItem)
                .info(info)
                .servers(servers);
    }
}
