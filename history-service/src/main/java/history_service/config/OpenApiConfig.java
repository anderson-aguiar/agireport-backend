package history_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("History-Service")
                        .version("1.0.0")
                        .description("Microservice responsável por fazer operações relacionadas ao histórico dos clientes.")
                        .contact(new Contact()
                                .name("Devs do Agi - Grupo 2")));
    }

}
