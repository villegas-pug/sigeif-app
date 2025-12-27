package microservice.cedif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = { "microservice.shared_data.entities" })
public class CedifApplication {

	public static void main(String[] args) {
		SpringApplication.run(CedifApplication.class, args);
	}

}
