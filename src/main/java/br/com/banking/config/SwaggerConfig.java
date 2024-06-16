package br.com.banking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Contas Bancárias")
                        .version("v1")
                        .description("Documentação da API de Contas Bancárias versão 1")
                        .termsOfService("https://banking.com/terms")
                        .contact(new Contact().name("Suporte").url("https://banking.com/support").email("support@banking.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .addSecurityItem(new SecurityRequirement().addList("OAuth2"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("OAuth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("https://auth-dev.princeton-lemitar.com.br/realms/princeton-lemitar/protocol/openid-connect/auth")
                                                .tokenUrl("https://auth-dev.princeton-lemitar.com.br/realms/princeton-lemitar/protocol/openid-connect/token")
                                                .scopes(new Scopes().addString("user-sinple-web-roles", "Access to user roles"))))));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-v1")
                .pathsToMatch("/api/v1/**")
                .build();
    }
}