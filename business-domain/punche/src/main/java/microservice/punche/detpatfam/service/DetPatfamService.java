package microservice.punche.detpatfam.service;

import java.util.List;
import microservice.punche.patfam.models.DetPatfam;

public interface DetPatfamService {

   void deleteDetPatfamById(Long idDetPatfam);

   DetPatfam findDetPatfamById(Long idDetPatfam);

   public List<DetPatfam> findDetPatfamByIdTaller(Integer idTaller);

   List<DetPatfam> findDetPatfamByParams(Integer idServicio, Integer idDynamic);

}
