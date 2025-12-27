package microservice.cedif.infrastructure.adapters.out.persistences.servicio;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Servicio;
import microservice.cedif.domain.ports.out.ServicioRepositoryPort;
import microservice.shared_data.entities.ServicioEntity;

@Repository
@AllArgsConstructor
public class ServicioRepositoryAdapter implements ServicioRepositoryPort {

   private final ServicioJpaRepository jpaRepository;
   private final ServicioEntityMapper mapper;

   @Override
   public List<Servicio> findAllServicios() {
      List<ServicioEntity> entities = this.jpaRepository.findAll();
      return this.mapper.toModels(entities);
   }

}
