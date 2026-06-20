package microservice.educalle.institucion.service;

import microservice.educalle.institucion.dtos.InstitucionCreateRequestDto;
import microservice.shared_data.entities.InstitucionEntity;

public interface InstitucionService {

   InstitucionEntity createInstitucion(InstitucionCreateRequestDto institucionDto);

   InstitucionEntity findInstitucionById(Long idInstitucion);

}
