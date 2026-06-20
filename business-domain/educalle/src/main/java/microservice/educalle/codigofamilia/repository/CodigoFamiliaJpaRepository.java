package microservice.educalle.codigofamilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.shared_data.entities.CodigoFamiliaEntity;

public interface CodigoFamiliaJpaRepository extends JpaRepository<CodigoFamiliaEntity, Long> {

}
