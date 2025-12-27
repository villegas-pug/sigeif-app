package microservice.scheduling.shared.repository;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class BaseRepository extends BaseOracleRepository {

   public BaseRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

}
