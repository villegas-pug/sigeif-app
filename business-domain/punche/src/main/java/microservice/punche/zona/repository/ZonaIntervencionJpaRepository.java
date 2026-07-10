package microservice.punche.zona.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.ZonaIntervencionEntity;

@Repository
public interface ZonaIntervencionJpaRepository extends JpaRepository<ZonaIntervencionEntity, Long> {

      @Query(value = """

                  SELECT DISTINCT z
                  FROM ZonaIntervencionEntity z
                  LEFT JOIN FETCH z.potencialesFamilias f
                  WHERE
                        UPPER(z.descripcion) = UPPER(:descripcion)
                        AND z.servicio.idServicio = :idServicio
                        AND z.estado = 1
                        AND z.eliminado = 0
                        AND (
                              f IS NULL OR (
                                    f.fecRegistra BETWEEN :fecIni AND :fecFin
                                    AND f.estado = 1
                                    AND f.eliminado = 0
                              )
                        )

                  """)
      List<ZonaIntervencionEntity> findByDescripcionIgnoreCaseAndFecRegistraBetweenAndServicio(
                  @Param("descripcion") String descripcion,
                  @Param("fecIni") LocalDate fecIni,
                  @Param("fecFin") LocalDate fecFin,
                  @Param("idServicio") Long idServicio);

      @Query(value = """

                  SELECT DISTINCT z
                  FROM ZonaIntervencionEntity z
                  JOIN FETCH z.potencialesFamilias pf
                  LEFT JOIN FETCH pf.codigoFamilia cf
                  LEFT JOIN FETCH pf.integrantesFamilia i
                  WHERE
                        pf.idFamilia IN :idsFamilia
                        AND z.estado = 1
                        AND z.eliminado = 0
                        AND pf.estado = 1
                        AND pf.eliminado = 0

                  """)
      List<ZonaIntervencionEntity> findByIdsFamilia(@Param("idsFamilia") List<Long> idsFamilia);

}
