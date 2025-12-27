package microservice.punche.anexofase.service;

import java.util.List;
import microservice.punche.anexofase.model.AnexoFase;

public interface AnexoFaseService {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
