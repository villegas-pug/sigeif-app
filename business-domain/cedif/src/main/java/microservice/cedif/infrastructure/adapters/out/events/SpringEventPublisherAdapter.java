package microservice.cedif.infrastructure.adapters.out.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.out.EventPublisherPort;
import microservice.shared_data.events.EventDomain;

@Component
@AllArgsConstructor
public class SpringEventPublisherAdapter implements EventPublisherPort {

   private final ApplicationEventPublisher eventPublisher;

   @Override
   public void publishEvent(EventDomain e) {
      this.eventPublisher.publishEvent(e);
   }

}
