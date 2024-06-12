package cw.exercise.controller;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server().url("http://localhost:8080")
                                    .description("Development");

        Contact apiContact = new Contact().name("Chris Woodham")
                                          .email("test@gmail.com");

        Info information = new Info().title("Take home exercise - Consultation API")
                                     .version("1.0")
                                     .description("This API exposes endpoints for working with the treatments offered by our company")
                                     .contact(apiContact);
        
        return new OpenAPI().info(information).servers(List.of(server));
    }

}

