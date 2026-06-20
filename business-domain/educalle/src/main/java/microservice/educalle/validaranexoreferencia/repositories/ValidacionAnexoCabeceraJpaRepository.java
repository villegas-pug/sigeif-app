package microservice.educalle.validaranexoreferencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.shared_data.entities.ValidacionAnexoCabeceraEntity;

public interface ValidacionAnexoCabeceraJpaRepository extends JpaRepository<ValidacionAnexoCabeceraEntity, Long> {

}
