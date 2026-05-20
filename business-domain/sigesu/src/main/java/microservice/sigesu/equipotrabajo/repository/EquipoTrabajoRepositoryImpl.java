package microservice.sigesu.equipotrabajo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.sigesu.equipotrabajo.dtos.EquipoTrabajoProjection;
import microservice.sigesu.equipotrabajo.enums.Cargos;
import microservice.sigesu.equipotrabajo.mappers.EquipoTrabajoEntityMapper;
import microservice.sigesu.equipotrabajo.model.EquipoTrabajo;
import microservice.sigesu.personal.repository.PersonalRepository;
import microservice.shared_data.entities.Cargo;
import microservice.shared_data.entities.EquipoTrabajoEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.ZonaIntervencionEntity;

@Repository
@AllArgsConstructor
public class EquipoTrabajoRepositoryImpl implements EquipoTrabajoRepository {

   private final EquipoTrabajoJpaRepository jpaRepository;
   private final PersonalRepository personalRepository;
   private final EquipoTrabajoEntityMapper mapper;

   @Override
   public EquipoTrabajo saveEquipoTrabajo(EquipoTrabajo equipoTrabajo) {

      Long idEquipoTrabajo = equipoTrabajo.getIdEquipo();

      EquipoTrabajoEntity newEquipoTrabajoEntity = new EquipoTrabajoEntity();

      if (idEquipoTrabajo != null) { // * Actualiza
         newEquipoTrabajoEntity = this.jpaRepository.findById(idEquipoTrabajo).map(newET -> {
            newET.setUsuActualiza(equipoTrabajo.getUsuActualiza());
            return newET;
         }).get();
      } else { // * Nuevo
         newEquipoTrabajoEntity.setZonaIntervencion(
               ZonaIntervencionEntity.builder().idZona(equipoTrabajo.getZonaIntervencion().getIdZona()).build());
         newEquipoTrabajoEntity.setUsuRegistra(equipoTrabajo.getUsuRegistra());
      }

      // * Común: Personal nuevo o existente.
      PersonalEntity newPersonal = this.personalRepository
            .findPersonalById(equipoTrabajo.getPersonal().getIdPersonal().intValue())
            .map(personal -> {
               personal.getPersona().setTelefono(equipoTrabajo.getPersonal().getPersona().getTelefono());
               personal.getPersona().setCorreo(equipoTrabajo.getPersonal().getPersona().getCorreo());
               return personal;
            }).get();

      newEquipoTrabajoEntity.setCargo(Cargo.builder().idCargo(equipoTrabajo.getCargo().getIdCargo()).build());
      newEquipoTrabajoEntity.setPersonal(newPersonal);

      return this.mapper.toModel(this.jpaRepository.save(newEquipoTrabajoEntity));

   }

   @Override
   public Optional<EquipoTrabajo> findEquipoTrabajoById(Long idEquipo) {
      return this.jpaRepository.findById(idEquipo).map(mapper::toModel);
   }

   @Override
   public void deleteEquipoTrabajoById(Long idEquipo) {
      this.jpaRepository.deleteById(idEquipo);
   }

   @Override
   public List<EquipoTrabajo> findAcompañantesByIdZona(Long idZona) {

      List<EquipoTrabajoProjection> acompañantes = this.jpaRepository
            .findEquiposByIdZona(idZona);

      return acompañantes
            .stream()
            .filter(equipo -> equipo.getCargo().getIdCargo().equals(Cargos.ACOMPAÑANTE.getId()))
            .map(this.mapper::toModel)
            .distinct() // * Evalua EqualAndHashcode por `idEquipo`
            .toList();

   }

}
