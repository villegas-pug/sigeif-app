package microservice.punche.persona.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import microservice.shared_data.entities.PersonaEntity;

@Repository
@AllArgsConstructor
public class PersonaRepositoryImpl implements PersonaRepository {

   private final PersonaJpaRepository repository;

   @Override
   public PersonaEntity savePersona(PersonaEntity persona) {
      return null;
   }

   @Override
   public Optional<PersonaEntity> findPersonaById(Long idPersona) {
      return this.repository.findById(idPersona);
   }

}
