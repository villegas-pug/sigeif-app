package microservice.punche.motivoreferecia.repository;

import java.util.List;

import microservice.punche.motivoreferecia.model.MotivoReferecia;

public interface MotivoReferenciaRepository {

   List<MotivoReferecia> findAllMotivosReferencia();

}
