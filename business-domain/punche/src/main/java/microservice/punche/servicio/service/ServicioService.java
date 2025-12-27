package microservice.punche.servicio.service;

import java.util.List;

import microservice.shared_data.entities.ServicioEntity;

public interface ServicioService {

   List<ServicioEntity> findAllServicios();

}
