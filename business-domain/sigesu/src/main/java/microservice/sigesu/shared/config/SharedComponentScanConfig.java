package microservice.sigesu.shared.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "microservice.sigesu", "microservice.shared_data" })
public class SharedComponentScanConfig {

}
