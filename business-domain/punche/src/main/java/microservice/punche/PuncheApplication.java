package microservice.punche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = { "microservice.shared_data.entities" })
public class PuncheApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuncheApplication.class, args);
	}

}
