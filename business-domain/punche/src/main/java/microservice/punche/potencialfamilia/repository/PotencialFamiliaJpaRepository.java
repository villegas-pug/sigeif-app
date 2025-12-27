package microservice.punche.potencialfamilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.shared_data.entities.PotencialFamiliaEntity;

public interface PotencialFamiliaJpaRepository extends JpaRepository<PotencialFamiliaEntity, Long> {

}
