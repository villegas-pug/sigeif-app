package microservice.educalle.contacto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
