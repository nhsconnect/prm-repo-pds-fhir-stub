package uk.nhs.prm.repo.pdsfhirstub.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Stub PDS Fhir", version = "v1"), servers = @Server(url = "/"))
public class SwaggerConfig {

}
