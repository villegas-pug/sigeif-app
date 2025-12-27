package microservice.punche.motivoreferecia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.shared_data.entities.MotivoReferenciaEntity;

public interface MotivoReferenciaJpaRepository extends JpaRepository<MotivoReferenciaEntity, Integer> {

}
