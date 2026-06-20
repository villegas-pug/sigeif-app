package microservice.educalle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = { "microservice.shared_data.entities" })
public class EducalleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducalleApplication.class, args);
	}

}
