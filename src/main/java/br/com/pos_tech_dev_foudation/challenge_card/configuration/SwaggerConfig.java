package br.com.pos_tech_dev_foudation.challenge_card.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Challenge Card")
                .version("v1.0.0")
                .description("Sistema para cadastro e gerenciamento de clientes, cartões e contratos.")
                .contact(new Contact()
                    .name("Time Dev_Foundations BB FIAP")
                    // .email("genaton@email.com")
                    .url("https://github.com/genaton/challenge_card"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT"))
            )
            .extensions(Map.of(
                "time", List.of(
                    Map.of("nome", "Genaton", "Dev", "Backend"),
                    Map.of("nome", "Elton Fabiano", "role", "DevOps"),
                    Map.of("nome", "Carla Aparecida Dutra", "role", "Documentação"),
                    Map.of("nome", "Moises Salgado", "role", "Documentação"),
                    Map.of("nome", "Renan Paschoalotti", "role", "Documentação")
                ),
                "x-repo", Map.of("url", "https://github.com/genaton/challenge_card", "branch", "main")
            ));
    }
}