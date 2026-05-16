package microservice.sigesu.anexofase.service;

import java.util.List;

import microservice.sigesu.anexofase.model.AnexoFase;

public interface AnexoFaseService {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
