package microservice.punche.shared.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "microservice.punche", "microservice.shared_data" })
public class SharedComponentScanConfig {

}
