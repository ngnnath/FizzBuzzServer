package engine.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger Configuration
 */
@Configuration
@Profile("local")
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info().title("FizzBuzz").description("Description des endpoint de FizzBuzz").version("1.0"));
    }

}