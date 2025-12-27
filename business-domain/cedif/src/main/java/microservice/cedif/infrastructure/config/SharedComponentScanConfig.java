package microservice.cedif.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "microservice.cedif", "microservice.shared_data" })
public class SharedComponentScanConfig {

}
