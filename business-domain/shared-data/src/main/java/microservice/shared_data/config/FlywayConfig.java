package microservice.shared_data.config;

// import javax.sql.DataSource;
// import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class FlywayConfig {

   // private final DataSource dataSource;

   /*
    * public FlywayConfig(DataSource dataSource) {
    * this.dataSource = dataSource;
    * }
    */

   @PostConstruct
   public void initMigration() {
      /*
       * Flyway.configure()
       * .dataSource(dataSource)
       * .locations("classpath:db/migration")
       * .load()
       * .migrate();
       */
   }

}
