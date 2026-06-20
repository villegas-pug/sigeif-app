package microservice.educalle.aliado.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.AliadoEntity;

@Repository
public interface AliadoJpaRepository extends JpaRepository<AliadoEntity, Long> {

   @Query(value = """
                     SELECT a
                     FROM AliadoEntity a
                     WHERE
                        a.eliminado = 0
                        AND a.zonaIntervencion.idZona = ?1
         """)
   List<AliadoEntity> findAliadosByIdZona(Long idZona);

}
