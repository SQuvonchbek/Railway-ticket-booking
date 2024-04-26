package uz.pdp.railwayticketproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Railway ticket application",
                description = "This application for booking railway tickets",
                version = "${app.version}",
                contact = @Contact(
                        name = "Quvonch",
                        email = "fendygamer2006@gmail.com"
                ),
                license = @License(
                name = "Apache 3.0",
                url = "https://starter.spring.io"
                ),
                summary = "The project is fully supported by G33 team"
        ),
        servers = {@Server(url = "http://localhost:8080/swagger",
                description = "Development Server")
        })

public class SwaggerConfiguration {


}
