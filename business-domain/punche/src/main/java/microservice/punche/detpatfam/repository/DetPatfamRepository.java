package microservice.punche.detpatfam.repository;

import java.util.List;
import java.util.Optional;
import microservice.punche.patfam.models.DetPatfam;
import microservice.punche.taller.model.Taller;
import microservice.shared_data.dtos.responses.DetPatfamProjectionResponse;

public interface DetPatfamRepository {

   void deleteDetPatfamById(Long idDetPatfam);

   Optional<DetPatfam> findDetPatfamById(Long idDetPatfam);

   List<DetPatfam> findDetPatfamByTaller(Taller taller);

   List<DetPatfam> findDetPatfamByParams(Integer idServicio, Integer idDynamic, Long idZona);

   List<DetPatfamProjectionResponse> findDetPatfamByIdTaller(Integer idTaller);

}
