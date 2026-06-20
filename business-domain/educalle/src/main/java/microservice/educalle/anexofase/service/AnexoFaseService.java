package microservice.educalle.anexofase.service;

import java.util.List;

import microservice.educalle.anexofase.model.AnexoFase;

public interface AnexoFaseService {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
