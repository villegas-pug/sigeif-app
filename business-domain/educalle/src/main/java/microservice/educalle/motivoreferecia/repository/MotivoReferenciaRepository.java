package microservice.educalle.motivoreferecia.repository;

import java.util.List;

import microservice.educalle.motivoreferecia.model.MotivoReferecia;

public interface MotivoReferenciaRepository {

   List<MotivoReferecia> findAllMotivosReferencia();

}
