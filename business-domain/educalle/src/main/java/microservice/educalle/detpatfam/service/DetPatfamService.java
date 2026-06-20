package microservice.educalle.detpatfam.service;

import java.util.List;
import microservice.educalle.patfam.models.DetPatfam;

public interface DetPatfamService {

   void deleteDetPatfamById(Long idDetPatfam);

   DetPatfam findDetPatfamById(Long idDetPatfam);

   public List<DetPatfam> findDetPatfamByIdTaller(Integer idTaller);

   List<DetPatfam> findDetPatfamByParams(Integer idServicio, Integer idDynamic);

}
