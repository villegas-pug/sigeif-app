package microservice.cedif.domain.ports.out;

import microservice.shared_data.events.EventDomain;

public interface EventPublisherPort {

   void publishEvent(EventDomain e);

}
