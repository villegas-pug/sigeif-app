package microservice.educalle.acta.service;

import java.util.List;

import microservice.educalle.acta.model.Acta;

public interface ActaService {

   void createActa(Acta acta);

   void updateActa(Acta acta);

   Acta findActaById(Long idActa);

   void deleteActaById(Long idActa);

   List<Acta> findActasByIdAliado(Long idAliado);

}
