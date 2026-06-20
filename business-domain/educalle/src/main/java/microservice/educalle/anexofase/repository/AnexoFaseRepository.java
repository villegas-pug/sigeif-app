package microservice.educalle.anexofase.repository;

import java.util.List;

import microservice.educalle.anexofase.model.AnexoFase;

public interface AnexoFaseRepository {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
