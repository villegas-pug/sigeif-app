package microservice.educalle.detpatfam.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.DetPatfamEntity;
import microservice.shared_data.entities.ModuloEntity;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;
import microservice.shared_data.entities.TallerEntity;

public interface DetPatfamJpaRepository extends JpaRepository<DetPatfamEntity, Long> {

   List<DetPatfamEntity> findByObjetivo(ObjetivoEspecificoEntity objetivo);

   List<DetPatfamEntity> findByModulo(ModuloEntity modulo);

   List<DetPatfamEntity> findByTaller(TallerEntity taller);

}
