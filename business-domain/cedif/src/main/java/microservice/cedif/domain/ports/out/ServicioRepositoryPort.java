package microservice.cedif.domain.ports.out;

import java.util.List;

import microservice.cedif.domain.models.Servicio;

public interface ServicioRepositoryPort {

   List<Servicio> findAllServicios();

}
