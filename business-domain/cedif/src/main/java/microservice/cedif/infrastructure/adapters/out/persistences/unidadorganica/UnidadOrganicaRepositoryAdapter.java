package microservice.cedif.infrastructure.adapters.out.persistences.unidadorganica;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.UnidadOrganica;
import microservice.cedif.domain.ports.out.UnidadOrganicaRepositoryPort;
import microservice.shared_data.entities.UnidadOrganicaEntity;

@Repository
@AllArgsConstructor
public class UnidadOrganicaRepositoryAdapter implements UnidadOrganicaRepositoryPort {

      private final UnidadOrganicaJpaRepository repository;
      private final UnidadOrganicaEntityMapper mapper;

      @Override
      public List<UnidadOrganica> findAllUnidadOrganicas() {
            List<UnidadOrganicaEntity> unidadesOrganicas = this.repository.findAll();
            return this.mapper.toModels(unidadesOrganicas);

      }

      @Override
      public List<UnidadOrganica> findUnidadesOrganicasByParams(Long idServicio, Integer anio, Integer mes,
                  String ref) {

            LocalDate fechaInicio, fechaFin;

            if (mes == -1) {
                  fechaInicio = LocalDate.of(anio, 1, 1);
                  fechaFin = LocalDate.of(anio, 12, 31);
            } else {
                  fechaInicio = LocalDate.of(anio, mes, 1);
                  fechaFin = fechaInicio.plusMonths(1).minusDays(1);
            }

            return this.repository.findUnidadesOrganicasByParams(idServicio, ref, fechaInicio, fechaFin)
                        .stream()
                        .map(this.mapper::toModel)
                        .toList();

      }

      @Override
      public List<UnidadOrganica> findUnidadesOrganicasByNombreReferencia(String ref) {
            return this.repository.findByNombreReferenciaContainingIgnoreCase(ref).stream().map(this.mapper::toModel)
                        .toList();
      }

}
