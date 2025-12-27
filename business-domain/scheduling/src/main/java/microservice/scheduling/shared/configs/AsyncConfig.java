package microservice.scheduling.shared.configs;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

   @Bean("startupTaskExecutor")
   public Executor startupTaskExecutor() {
      ThreadPoolTaskExecutor excutor = new ThreadPoolTaskExecutor();
      excutor.setCorePoolSize(5);
      excutor.setThreadNamePrefix("startup-task-executor-");
      excutor.initialize();
      return excutor;
   }

}
