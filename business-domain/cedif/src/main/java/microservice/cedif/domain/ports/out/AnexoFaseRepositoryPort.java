package microservice.cedif.domain.ports.out;

import java.util.List;

import microservice.cedif.domain.models.AnexoFase;

public interface AnexoFaseRepositoryPort {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
