package microservice.educalle.persona.repository;

import java.util.Optional;

import microservice.shared_data.entities.PersonaEntity;

public interface PersonaRepository {

   PersonaEntity savePersona(PersonaEntity persona);

   Optional<PersonaEntity> findPersonaById(Long idPersona);

}
