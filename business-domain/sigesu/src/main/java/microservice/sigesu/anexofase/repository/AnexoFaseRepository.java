package microservice.sigesu.anexofase.repository;

import java.util.List;

import microservice.sigesu.anexofase.model.AnexoFase;

public interface AnexoFaseRepository {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
