package microservice.educalle.institucion.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.institucion.dtos.InstitucionCreateRequestDto;
import microservice.educalle.institucion.mappers.InstitucionMapper;
import microservice.educalle.institucion.repository.InstitucionRepository;
import microservice.shared_data.entities.InstitucionEntity;

@Service
@AllArgsConstructor
public class InstitucionServiceImpl implements InstitucionService {

   private final InstitucionRepository repository;
   private final InstitucionMapper mapper;

   @Override
   @Transactional
   public InstitucionEntity createInstitucion(InstitucionCreateRequestDto institucionDto) {
      InstitucionEntity newInstitucion = new InstitucionEntity();
      this.mapper.fromCreateDtoEntity(institucionDto, newInstitucion);
      return this.repository.save(newInstitucion);
   }

   @Override
   @Transactional(readOnly = true)
   public InstitucionEntity findInstitucionById(Long idInstitucion) {
      throw new UnsupportedOperationException("Unimplemented method 'findInstitucionById'");
   }

}
