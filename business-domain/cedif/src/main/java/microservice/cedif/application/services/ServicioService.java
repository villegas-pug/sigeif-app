package microservice.cedif.application.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Servicio;
import microservice.cedif.domain.ports.in.servicio.ServicioServicePort;
import microservice.cedif.domain.ports.out.ServicioRepositoryPort;

@Service
@AllArgsConstructor
public class ServicioService implements ServicioServicePort {

   private final ServicioRepositoryPort repository;

   @Override
   @Transactional(readOnly = true)
   public List<Servicio> findAllServicios() {
      List<Servicio> servicios = this.repository.findAllServicios();
      return servicios;
   }

}
