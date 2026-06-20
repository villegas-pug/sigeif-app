package microservice.educalle.equipotrabajo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.equipotrabajo.dtos.EquipoTrabajoResponse;
import microservice.educalle.equipotrabajo.mappers.EquipoTrabajoResponseMapper;
import microservice.educalle.equipotrabajo.model.EquipoTrabajo;
import microservice.educalle.equipotrabajo.repository.EquipoTrabajoRepository;
import microservice.shared_data.exceptions.NotFoundByIdException;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class EquipoTrabajoServiceImpl implements EquipoTrabajoService {

   private final EquipoTrabajoRepository repository;
   private final EquipoTrabajoResponseMapper responseMapper;

   @Override
   @Transactional
   public EquipoTrabajo createEquipoTrabajo(EquipoTrabajo equipoTrabajoDto) {
      return this.repository.saveEquipoTrabajo(equipoTrabajoDto);
   }

   @Override
   @Transactional(readOnly = true)
   public EquipoTrabajo findEquipoTrabajoById(Long idEquipo) {
      return this.repository.findEquipoTrabajoById(idEquipo)
            .orElseThrow(() -> new NotFoundByIdException(idEquipo));
   }

   @Override
   @Transactional
   public EquipoTrabajo updateEquipoTrabajo(EquipoTrabajo equipoTrabajo) {
      return this.repository.saveEquipoTrabajo(equipoTrabajo);
   }

   @Override
   @Transactional
   public void deleteEquipoTrabajoById(Long idEquipo) {
      this.repository.deleteEquipoTrabajoById(idEquipo);
   }

   @Override
   @Transactional(readOnly = true)
   public List<EquipoTrabajoResponse> findAcompañantesByIdZona(Long idZona) {
      List<EquipoTrabajo> acompañantes = this.repository.findAcompañantesByIdZona(idZona);
      if (acompañantes.size() == 0) {
         throw new NotFoundException();
      }

      return acompañantes.stream().map(this.responseMapper::toResponse).toList();
   }

}
