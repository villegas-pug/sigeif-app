package microservice.educalle.contacto.service;

import java.util.List;

import microservice.shared_data.entities.Contacto;
import microservice.educalle.contacto.dtos.*;

public interface ContactoService {

   List<Contacto> findAllContactos();

   ContactoResponseDto findContactoById(Long idContacto);

   ContactoResponseDto createContacto(ContactoCreateRequestDto contactoDto);

   ContactoResponseDto updateContacto(ContactoUpdateRequestDto contactoDto);

   void deleteContactoById(Long idContacto);

}
