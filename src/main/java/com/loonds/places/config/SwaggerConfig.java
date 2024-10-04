package com.loonds.places.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@OpenAPIDefinition(info = @Info(title = "Follow APIs", version = "1.0"),
//        servers = {@Server(url = "https://dapi.carvia.tech/follow-service", description = "Dev Server"),
//                @Server(url = "http://localhost:8080", description = "Local Server")})
@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Portfolio Service REST-API")
                        .description("This is official API documentation for profile project.")
                        .version("snapshot")
                        .contact(new Contact().name("Pankaj Kumar").url("https://www.pahariyatri.com/"))
                        .license(new License().name("Copy Right").url("https://github.com/thombergs/code-examples/blob/master/LICENSE"))
                        .termsOfService("/contact.html"));
//                .components(new Components()
//                        .addSecuritySchemes("bearer-jwt",
//                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//                                        .in(SecurityScheme.In.HEADER).name("Authorization")))
//                .addSecurityItem(
//                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
}
