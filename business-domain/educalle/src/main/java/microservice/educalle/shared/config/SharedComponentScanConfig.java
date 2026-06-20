package microservice.educalle.shared.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "microservice.educalle", "microservice.shared_data" })
public class SharedComponentScanConfig {

}
