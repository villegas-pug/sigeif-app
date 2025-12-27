package microservice.cedif.infrastructure.adapters.out.persistences.potencialfamilia;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.shared_data.entities.PotencialFamiliaEntity;

public interface PotencialFamiliaJpaRepository extends JpaRepository<PotencialFamiliaEntity, Long> {

}
