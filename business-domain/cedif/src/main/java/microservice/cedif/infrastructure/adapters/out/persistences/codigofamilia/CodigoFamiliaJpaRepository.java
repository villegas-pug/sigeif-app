package microservice.cedif.infrastructure.adapters.out.persistences.codigofamilia;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.shared_data.entities.CodigoFamiliaEntity;

public interface CodigoFamiliaJpaRepository extends JpaRepository<CodigoFamiliaEntity, Long> {

}
