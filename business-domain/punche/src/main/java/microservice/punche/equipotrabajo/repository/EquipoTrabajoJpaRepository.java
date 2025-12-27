package microservice.punche.equipotrabajo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import microservice.punche.equipotrabajo.dtos.EquipoTrabajoProjection;
import microservice.shared_data.entities.EquipoTrabajoEntity;

@Repository
public interface EquipoTrabajoJpaRepository extends JpaRepository<EquipoTrabajoEntity, Long> {

   @Query("""
                  SELECT
                     et.idEquipo as idEquipo,
                     et.cargo as cargo,
                     et.personal as personal,
                     et.eliminado as eliminado
                  FROM EquipoTrabajoEntity et
                  WHERE
                     et.zonaIntervencion.idZona = :idZona
         """)
   List<EquipoTrabajoProjection> findEquiposByIdZona(@Param("idZona") Long idZona);

}
