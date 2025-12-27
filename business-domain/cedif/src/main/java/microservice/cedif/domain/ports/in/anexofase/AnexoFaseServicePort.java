package microservice.cedif.domain.ports.in.anexofase;

import java.util.List;

import microservice.cedif.domain.models.AnexoFase;

public interface AnexoFaseServicePort {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
