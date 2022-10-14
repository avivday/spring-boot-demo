package com.aviv.springbootdemo.webapi.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = { @Server(url = "https://localhost:8080") }, info = @Info(title = "Your API Title", description = "Small api description"))
@SecurityScheme(name = "myOauth2Security",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.HEADER,
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "http://url.com/auth",
                        scopes = @OAuthScope(name = "write:pets", description = "modify pets in your account"))))
@SecurityScheme(name = "apiKey", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "API_KEY")
public class OpenApiConfig {

    /**
     * Adds v1 to the open-api definition
     * @return V1 version
     */
    @Bean
    public GroupedOpenApi ApiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/v1/**/**")
                .build();
    }

    /**
     * Currently, we don't have any v2, but this un-comment this example  to add more versions
     * @return Version group
     */
//    @Bean
//    public GroupedOpenApi ApiV2() {
//        return GroupedOpenApi.builder()
//                .group("v2")
//                .pathsToMatch("/api/v2/**/**")
//                .build();
//    }
}
