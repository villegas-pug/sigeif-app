package microservice.punche.servicio.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.servicio.repository.ServicioRepository;
import microservice.shared_data.entities.ServicioEntity;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class ServicioServiceImpl implements ServicioService {

   private final ServicioRepository repository;

   @Override
   @Transactional(readOnly = true)
   public List<ServicioEntity> findAllServicios() {

      List<ServicioEntity> servicios = this.repository.findAll();
      if (servicios.size() == 0) {
         throw new NotFoundException();
      }

      return servicios;
   }

}
