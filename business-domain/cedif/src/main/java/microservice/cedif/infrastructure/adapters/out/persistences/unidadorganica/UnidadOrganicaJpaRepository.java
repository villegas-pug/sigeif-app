package microservice.cedif.infrastructure.adapters.out.persistences.unidadorganica;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.UnidadOrganicaEntity;

@Repository
public interface UnidadOrganicaJpaRepository extends JpaRepository<UnidadOrganicaEntity, Long> {

   @Query(value = """
                        SELECT
                           uo.idUO AS idUO,
                           uo.nombreReferencia AS nombreReferencia,
                           uo.telefono AS telefono,
                           uo.direccion AS direccion,
                           uo.referencia AS referencia,
                           uo.correo AS correo,
                           uo.ubigeo AS ubigeo,
                           uo.representante AS representante
                        FROM UnidadOrganicaEntity uo
                        WHERE
                           UPPER(uo.nombreReferencia) LIKE '%' || UPPER(:ref) || '%'
                        ORDER BY uo.nombreReferencia ASC
         """)
   List<UnidadOrganicaProjection> findByNombreReferenciaContainingIgnoreCase(@Param("ref") String ref);

   @Query(value = """
                        SELECT DISTINCT uo
                        FROM UnidadOrganicaEntity uo
                        JOIN FETCH uo.potencialesFamilias pf
                        WHERE
                           pf.servicio.idServicio = :idServicio
                           AND UPPER(uo.nombreReferencia) = UPPER(:ref)
                           AND pf.fecRegistra BETWEEN :fechaInicio AND :fechaFin
                           AND pf.estado = 1
                           AND pf.eliminado = 0
                        ORDER BY uo.nombreReferencia ASC
         """)
   List<UnidadOrganicaEntity> findUnidadesOrganicasByParams(
         @Param("idServicio") Long idServicio,
         @Param("ref") String ref,
         @Param("fechaInicio") LocalDate fechaInicio,
         @Param("fechaFin") LocalDate fechaFin);

}