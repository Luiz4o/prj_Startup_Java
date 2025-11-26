package com.startup.vanguard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Peca Já API", version = "1",
                contact = @Contact(name = "Luiz Henrique",
                        email = "henriqueluiz1103@gmail.com", url = ""),
                license = @License(name = "Apache 2.0", url =
                        "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "", description =
                ""),
        servers = {
                    @Server(
                            url = "http://localhost:8080",
                            description = "HML"
                    ),
                    @Server(
                            url = "https://prj-startup-java.onrender.com",
                            description = "PRD"
                    )
            })
@SecurityScheme(name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
public class SwaggerConfig {
}
