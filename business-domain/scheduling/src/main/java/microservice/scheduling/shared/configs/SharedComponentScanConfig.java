package microservice.scheduling.shared.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "microservice.scheduling", "microservice.shared_data" })
public class SharedComponentScanConfig {

}
