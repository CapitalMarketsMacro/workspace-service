package org.capitalmarkets.workspaceservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI workspaceServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workspace Service API")
                        .description("REST API for dock configuration and workspace management — Capital Markets")
                        .version("0.0.1-SNAPSHOT"));
    }
}
