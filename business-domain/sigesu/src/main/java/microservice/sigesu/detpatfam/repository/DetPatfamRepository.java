package microservice.sigesu.detpatfam.repository;

import java.util.List;
import java.util.Optional;
import microservice.sigesu.patfam.models.DetPatfam;
import microservice.sigesu.taller.model.Taller;
import microservice.shared_data.dtos.responses.DetPatfamProjectionResponse;

public interface DetPatfamRepository {

   void deleteDetPatfamById(Long idDetPatfam);

   Optional<DetPatfam> findDetPatfamById(Long idDetPatfam);

   List<DetPatfam> findDetPatfamByTaller(Taller taller);

   List<DetPatfam> findDetPatfamByParams(Integer idServicio, Integer idDynamic);

   List<DetPatfamProjectionResponse> findDetPatfamByIdTaller(Integer idTaller);

}
