package com.freela.freelancer.Config;


import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SweggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("app Frealanccer Luan Vagabundo ").description("api para gerenciamento de caditados e vagas").version("1.0.0"))
                .schemaRequirement("jwt_token",securityScheme());


    }
    private SecurityScheme securityScheme(){
        return new SecurityScheme().name("jwt_token").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").in(SecurityScheme.In.HEADER);
    }
}
