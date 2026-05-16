package microservice.sigesu.contacto.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.sigesu.contacto.repository.ContactoRepository;
import microservice.sigesu.contacto.dtos.*;
import microservice.sigesu.contacto.mappers.ContactoMapper;
import microservice.shared_data.entities.Contacto;
import microservice.shared_data.exceptions.NotFoundByIdException;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class ContactoServiceImpl implements ContactoService {

   private final ContactoRepository repository;
   private final ContactoMapper mapper;

   @Override
   @Transactional(readOnly = true)
   public List<Contacto> findAllContactos() {
      List<Contacto> contactos = this.repository.findAll();
      if (contactos.size() == 0) {
         throw new NotFoundException();
      }
      return contactos;
   }

   @Override
   @Transactional(readOnly = true)
   public ContactoResponseDto findContactoById(Long idContacto) {
      Contacto contacto = this.repository.findById(idContacto).orElseThrow(() -> new NotFoundByIdException(idContacto));
      return this.mapper.toResponseDto(contacto);
   }

   @Override
   @Transactional
   public ContactoResponseDto createContacto(ContactoCreateRequestDto contactoDto) {
      Contacto newContacto = new Contacto();
      mapper.fromCreateDtoToEntity(contactoDto, newContacto);
      newContacto = this.repository.save(newContacto);
      return this.mapper.toResponseDto(newContacto);
   }

   @Override
   @Transactional
   public ContactoResponseDto updateContacto(ContactoUpdateRequestDto contactoDto) {
      Long idContacto = contactoDto.getIdContacto();
      Contacto oldContacto = this.repository.findById(idContacto)
            .orElseThrow(() -> new NotFoundByIdException(idContacto));
      mapper.fromUpdateDtoToEntity(contactoDto, oldContacto);
      Contacto updatedContacto = this.repository.save(oldContacto);
      return this.mapper.toResponseDto(updatedContacto);
   }

   @Override
   @Transactional
   public void deleteContactoById(Long idContacto) {
      Contacto contacto = this.repository.findById(idContacto)
            .orElseThrow(() -> new NotFoundByIdException(idContacto));

      contacto.setEliminado(1);
      this.repository.save(contacto);
   }

}
