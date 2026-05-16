package microservice.sigesu.motivoreferecia.repository;

import java.util.List;

import microservice.sigesu.motivoreferecia.model.MotivoReferecia;

public interface MotivoReferenciaRepository {

   List<MotivoReferecia> findAllMotivosReferencia();

}
