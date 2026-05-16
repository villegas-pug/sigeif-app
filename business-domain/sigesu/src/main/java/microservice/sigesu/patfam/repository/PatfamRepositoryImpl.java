package microservice.sigesu.patfam.repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.sigesu.detpatfam.mappers.DetPatfamEntityMapper;
import microservice.sigesu.detpatfam.repository.DetPatfamJpaRepository;
import microservice.sigesu.objetivoespecifico.repository.ObjetivoEspecificoRepository;
import microservice.sigesu.patfam.mappers.PatfamEntityMapper;
import microservice.sigesu.patfam.models.DetPatfam;
import microservice.sigesu.patfam.models.Patfam;
import microservice.sigesu.unidadsesion.mappers.UnidadSesionEntityMapper;
import microservice.sigesu.unidadsesion.model.UnidadSesion;
import microservice.sigesu.unidadsesion.repository.UnidadSesionJpaRepository;
import microservice.shared_data.entities.DetPatfamEntity;
import microservice.shared_data.entities.PatfamEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadSesionEntity;

@Repository
@AllArgsConstructor
public class PatfamRepositoryImpl implements PatfamRepository {

   private final PatfamJpaRepository patfamJpaRepository;
   private final DetPatfamJpaRepository detPatfamJpaRepository;
   private final ObjetivoEspecificoRepository objetivoRepository;
   private final UnidadSesionJpaRepository unidadJpaRepository;
   private final PatfamEntityMapper patfamMapper;
   private final DetPatfamEntityMapper detPatfamMapper;
   private final UnidadSesionEntityMapper sesionMapper;

   @Override
   public Patfam createPatfam(Patfam patfam) {

      // * Obtiene `Id` servicio
      Integer idObjetivo = patfam.getDetPatfam().get(0).getObjetivo().getIdObjetivo();
      Long idServicio = this.objetivoRepository.findObjetivoById(idObjetivo).get().getServicio().getIdServicio();

      // * Cabecera:
      PatfamEntity createPatfam = this.patfamMapper.toEntity(patfam);
      PatfamEntity createdPatfam = this.patfamJpaRepository.save(createPatfam);

      // * Detalle:
      createPatfam.getDetPatfam().forEach(detPatfam -> {

         detPatfam.setPatfam(createdPatfam);

         // * Tema suministrado en Punche
         if (detPatfam.getTema() != null) {

            // * Limpia sesion suministrada en el mapper
            detPatfam.setSesion(null);

            this.detPatfamJpaRepository.save(detPatfam);
            return;
         }

         // * Suministrador en todos los servicios
         if (detPatfam.getSesion().getIdSesion() == null && detPatfam.getSesion().getNombre() != null) { // * Nueva
                                                                                                         // sesión

            UnidadSesionEntity createSesion = detPatfam.getSesion();

            switch (idServicio.intValue()) {
               case 1 -> createSesion.setModulo(detPatfam.getModulo()); // * 1 -> Cedif
               case 2 -> createSesion.setUnidad(detPatfam.getUnidad()); // * 2 -> Punche
               case 3 -> createSesion.setObjetivo(detPatfam.getObjetivo()); // * 3 -> Acercandonos
            }

            UnidadSesionEntity createdSesion = this.unidadJpaRepository.save(createSesion);
            detPatfam.setSesion(createdSesion);
         }

         this.detPatfamJpaRepository.save(detPatfam);
      });

      return this.patfamMapper.toModel(createPatfam);
   }

   @Override
   public Patfam updatePatfam(Patfam patfam) {

      // * Obtiene `Id` servicio
      Integer idObjetivo = patfam.getDetPatfam().get(0).getObjetivo().getIdObjetivo();
      Long idServicio = this.objetivoRepository.findObjetivoById(idObjetivo).get().getServicio().getIdServicio();

      // * 1. Cabecera:
      PatfamEntity oldPatfam = this.patfamJpaRepository.findById(patfam.getIdPatfam()).get();

      // * Ids del detalle que vienen de la base de datos
      Set<Long> idsDetPatfamPersit = oldPatfam.getDetPatfam()
            .stream()
            .map(DetPatfamEntity::getIdDetPatfam)
            .collect(Collectors.toSet());

      // * Merge
      this.patfamMapper.fromModelToEntity(patfam, oldPatfam);

      // * 2. Detalle:
      patfam.getDetPatfam().forEach(detPatfam -> {

         Long idDetPatfam = detPatfam.getIdDetPatfam();
         DetPatfamEntity oldDetPatfam = new DetPatfamEntity();

         if (idDetPatfam != null) { // * Para actualizar
            oldDetPatfam = this.detPatfamJpaRepository.findById(idDetPatfam).get();
         }

         // * Tema únicamente suministrado en Punche
         if (detPatfam.getTema() != null) {
            detPatfam.setSesion(null);// * Limpia sesion suministrada en el mapper

            this.detPatfamMapper.fromModelToEntity(detPatfam, oldDetPatfam);
            oldDetPatfam.setPatfam(oldPatfam);
            oldDetPatfam.setSesion(null); // * Elimina sesion en caso registre
            this.detPatfamJpaRepository.save(oldDetPatfam);
            return;
         }

         // * Crea sesión
         if (detPatfam.getSesion().getIdSesion() == null && detPatfam.getSesion().getNombre() != null) {
            UnidadSesion createSesion = detPatfam.getSesion();

            switch (idServicio.intValue()) {
               case 1 -> createSesion.setModulo(detPatfam.getModulo()); // * 1 -> Cedif
               case 2 -> createSesion.setUnidad(detPatfam.getUnidad()); // * 2 -> Punche
               case 3 -> createSesion.setObjetivo(detPatfam.getObjetivo()); // * 3 -> Acercandonos
            }

            UnidadSesionEntity createdSesion = this.unidadJpaRepository.save(this.sesionMapper.toEntity(createSesion));
            detPatfam.setSesion(this.sesionMapper.toModel(createdSesion));
         }

         this.detPatfamMapper.fromModelToEntity(detPatfam, oldDetPatfam);
         oldDetPatfam.setPatfam(oldPatfam);
         oldDetPatfam.setTema(null); // * Elimina tema en caso registre
         this.detPatfamJpaRepository.save(oldDetPatfam);

      });

      // * Elimina registros persistidos que no se incluyeron en la actualización
      Set<Long> idsDetPatfamSource = new HashSet<>(
            patfam.getDetPatfam()
                  .stream()
                  .map(DetPatfam::getIdDetPatfam)
                  .filter(Objects::nonNull)
                  .collect(Collectors.toSet()));

      for (Long idDetPatfamPersist : idsDetPatfamPersit) {
         if (!idsDetPatfamSource.contains(idDetPatfamPersist)) {
            this.detPatfamJpaRepository.findById(idDetPatfamPersist).map(detPatfam -> {
               detPatfam.setEliminado(1); // ! Eliminado
               return detPatfam;
            });
         }
      }

      return this.patfamMapper.toModel(oldPatfam);
   }

   @Override
   public Optional<Patfam> findPatfamByIdFamilia(Long idFamilia) {
      return this.patfamJpaRepository
            .findTop1ByFamiliaOrderByIdPatfamDesc(PotencialFamiliaEntity.builder().idFamilia(idFamilia).build())
            .map(this.patfamMapper::toModel);
   }

   @Override
   public void deletePatfamById(Long idPatfam) {
      this.patfamJpaRepository.findById(idPatfam).map(patfam -> {
         patfam.setEliminado(1); // ! Eliminado
         return patfam;
      });
   }

}
