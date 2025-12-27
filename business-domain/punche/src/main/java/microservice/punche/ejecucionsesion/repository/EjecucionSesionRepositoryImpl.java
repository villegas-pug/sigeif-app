package microservice.punche.ejecucionsesion.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.punche.ejecucionsesion.mappers.EjecucionSesionEntityMapper;
import microservice.punche.ejecucionsesion.model.EjecucionSesion;
import microservice.punche.ejecucionsesionintegrante.mappers.EjecucionSesionIntegranteEntityMapper;
import microservice.punche.ejecucionsesionintegrante.repository.EjecucionSesionIntegranteJpaRepository;
import microservice.shared_data.entities.EjecucionSesionEntity;
import microservice.shared_data.entities.EjecucionSesionIntegranteEntity;

@Repository
@AllArgsConstructor
public class EjecucionSesionRepositoryImpl implements EjecucionSesionRepository {

      private final EjecucionSesionJpaRepository ejecSesionJpaRepository;
      private final EjecucionSesionIntegranteJpaRepository ejecSesionIntegranteJpaRepository;
      private final EjecucionSesionEntityMapper ejecSesionMapper;
      private final EjecucionSesionIntegranteEntityMapper ejecIntegranteMapper;

      @Override
      public EjecucionSesion createEjecucionSesion(EjecucionSesion ejecucionSesion) {

            // * Cabecera
            EjecucionSesionEntity newEjecucionSesion = new EjecucionSesionEntity();
            this.ejecSesionMapper.fromModelToEntity(ejecucionSesion, newEjecucionSesion);
            newEjecucionSesion = this.ejecSesionJpaRepository.save(newEjecucionSesion);

            // * Detalle
            List<EjecucionSesionIntegranteEntity> newEjecucionSesionIntegrantes = ejecucionSesion.getIntegrantes()
                        .stream()
                        .map(this.ejecIntegranteMapper::toEntity)
                        .collect(Collectors.toList());

            final EjecucionSesionEntity finalNewEjecucionSesion = newEjecucionSesion;
            newEjecucionSesionIntegrantes
                        .stream()
                        .peek(integrante -> integrante.setEjecucionSesion(finalNewEjecucionSesion))
                        .forEach(this.ejecSesionIntegranteJpaRepository::save);

            return this.ejecSesionMapper.toModel(newEjecucionSesion);

      }

      @Override
      public EjecucionSesion updateEjecucionSesion(EjecucionSesion ejecucionSesion) {

            // * Cabecera
            EjecucionSesionEntity oldEjecucionSesion = this.ejecSesionJpaRepository
                        .findById(ejecucionSesion.getIdEjecucion()).get();
            List<EjecucionSesionIntegranteEntity> oldSesionIntegrantes = new ArrayList<>(
                        oldEjecucionSesion.getIntegrantes());
            this.ejecSesionMapper.fromModelToEntity(ejecucionSesion, oldEjecucionSesion);

            // * Detalle
            ejecucionSesion.getIntegrantes()
                        .stream()
                        .map(this.ejecIntegranteMapper::toEntity)
                        .peek(integrante -> integrante.setEjecucionSesion(oldEjecucionSesion))
                        .peek(sesionIntegrante -> {
                              oldSesionIntegrantes
                                          .stream()
                                          .filter(oldSesionIntegrante -> oldSesionIntegrante
                                                      .getIntegranteFamilia()
                                                      .getIdIntegrante()
                                                      .equals(sesionIntegrante.getIntegranteFamilia()
                                                                  .getIdIntegrante()))
                                          .forEach(oldSesionIntegrante -> sesionIntegrante.setIdSesionIntegrante(
                                                      oldSesionIntegrante.getIdSesionIntegrante()));
                        })
                        .forEach(this.ejecSesionIntegranteJpaRepository::save);

            return this.ejecSesionMapper.toModel(oldEjecucionSesion);

      }

      @Override
      public void deleteEjecucionSesionById(Long idEjecucionSesion) {
            this.ejecSesionJpaRepository.findById(idEjecucionSesion)
                        .ifPresent(ejecucionSesion -> {
                              ejecucionSesion.setEliminado(1);
                              this.ejecSesionJpaRepository.save(ejecucionSesion);
                        });
      }

      @Override
      public void disableEjecucionSesionById(Long idEjecucionSesion) {
            this.ejecSesionJpaRepository.findById(idEjecucionSesion)
                        .ifPresent(ejecucionSesion -> ejecucionSesion.setEstado(0)); // ! (0) Disabled
      }

      @Override
      public Optional<EjecucionSesion> findEjecucionSesionById(Long idEjecSesion) {
            return this.ejecSesionJpaRepository.findById(idEjecSesion).map(this.ejecSesionMapper::toModel);
      }

      @Override
      public void uploadAnexoEjecucionSesion(Long idEjecSesion, String anexoNombre, byte[] anexo) {
            this.ejecSesionJpaRepository.findById(idEjecSesion)
                        .ifPresent(ejecucionSesion -> {
                              ejecucionSesion.setAnexoNombre(anexoNombre);
                              ejecucionSesion.setAnexo(anexo);
                              this.ejecSesionJpaRepository.save(ejecucionSesion);
                        });
      }

}
