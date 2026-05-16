package microservice.sigesu.acta.service;

import java.util.List;

import microservice.sigesu.acta.model.Acta;

public interface ActaService {

   void createActa(Acta acta);

   void updateActa(Acta acta);

   Acta findActaById(Long idActa);

   void deleteActaById(Long idActa);

   List<Acta> findActasByIdAliado(Long idAliado);

}
