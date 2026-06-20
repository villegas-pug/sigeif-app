package microservice.educalle.gruposocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.shared_data.entities.GrupoSocialEntity;

@Repository
public interface GrupoSocialRepository extends JpaRepository<GrupoSocialEntity, Integer> {

}