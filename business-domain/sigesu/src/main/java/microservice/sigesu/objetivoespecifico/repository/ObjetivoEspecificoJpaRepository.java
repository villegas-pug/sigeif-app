package microservice.sigesu.objetivoespecifico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;

public interface ObjetivoEspecificoJpaRepository extends JpaRepository<ObjetivoEspecificoEntity, Integer> {

}
