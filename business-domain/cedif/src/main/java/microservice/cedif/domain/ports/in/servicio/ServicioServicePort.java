package microservice.cedif.domain.ports.in.servicio;

import java.util.List;

import microservice.cedif.domain.models.Servicio;

public interface ServicioServicePort {

   List<Servicio> findAllServicios();

}
