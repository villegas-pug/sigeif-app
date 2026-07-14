package microservice.punche.potencialfamilia.repository;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import microservice.shared_data.entities.CodigoFamiliaEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;

public interface PotencialFamiliaJpaRepository extends JpaRepository<PotencialFamiliaEntity, Long> {

      @Query(value = """
                  SELECT pf.idFamilia
                  FROM PotencialFamiliaEntity pf
                  JOIN pf.zonaIntervencion z
                  WHERE UPPER(z.descripcion) = UPPER(:descripcion)
                        AND z.servicio.idServicio = :idServicio
                        AND z.estado = 1
                        AND z.eliminado = 0
                        AND pf.fecRegistra BETWEEN :fecIni AND :fecFin
                        AND pf.estado = 1
                        AND pf.eliminado = 0
                        AND (
                              :codFamilia IS NULL
                              OR TRIM(:codFamilia) = ''
                              OR EXISTS (
                                    SELECT 1 FROM CodigoFamiliaEntity cf
                                    WHERE cf.familia.idFamilia = pf.idFamilia
                                          AND UPPER(cf.codigo) LIKE UPPER(CONCAT('%', :codFamilia, '%'))
                              )
                        )
                  ORDER BY
                        (
                              SELECT MIN(cf2.codigo)
                              FROM CodigoFamiliaEntity cf2
                              WHERE cf2.familia.idFamilia = pf.idFamilia
                        ) ASC NULLS LAST
                  """, countQuery = """
                  SELECT COUNT(pf.idFamilia)
                  FROM PotencialFamiliaEntity pf
                  JOIN pf.zonaIntervencion z
                  WHERE UPPER(z.descripcion) = UPPER(:descripcion)
                        AND z.servicio.idServicio = :idServicio
                        AND z.estado = 1
                        AND z.eliminado = 0
                        AND pf.fecRegistra BETWEEN :fecIni AND :fecFin
                        AND pf.estado = 1
                        AND pf.eliminado = 0
                        AND (
                              :codFamilia IS NULL
                              OR TRIM(:codFamilia) = ''
                              OR EXISTS (
                                    SELECT 1 FROM CodigoFamiliaEntity cf
                                    WHERE cf.familia.idFamilia = pf.idFamilia
                                          AND UPPER(cf.codigo) LIKE UPPER(CONCAT('%', :codFamilia, '%'))
                              )
                        )
                  """)
      Page<Long> findIdsByZonaParams(
                  @Param("descripcion") String descripcion,
                  @Param("fecIni") LocalDate fecIni,
                  @Param("fecFin") LocalDate fecFin,
                  @Param("idServicio") Long idServicio,
                  @Param("codFamilia") String codFamilia,
                  Pageable pageable);

}
