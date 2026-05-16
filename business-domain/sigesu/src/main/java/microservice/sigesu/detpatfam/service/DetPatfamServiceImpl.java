package microservice.sigesu.detpatfam.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.sigesu.detpatfam.repository.DetPatfamRepository;
import microservice.sigesu.patfam.models.DetPatfam;
import microservice.sigesu.taller.model.Taller;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class DetPatfamServiceImpl implements DetPatfamService {

   private final DetPatfamRepository repository;

   @Override
   @Transactional
   public void deleteDetPatfamById(Long idDetPatfam) {
      this.repository.deleteDetPatfamById(idDetPatfam);
   }

   @Override
   @Transactional(readOnly = true)
   public DetPatfam findDetPatfamById(Long idDetPatfam) {
      return this.repository.findDetPatfamById(idDetPatfam).orElseThrow(NotFoundException::new);
   }

   @Override
   @Transactional(readOnly = true)
   public List<DetPatfam> findDetPatfamByIdTaller(Integer idTaller) {
      List<DetPatfam> detPatfam = this.repository.findDetPatfamByTaller(Taller.builder().idTaller(idTaller).build());

      if (detPatfam.isEmpty()) {
         throw new NotFoundException();
      }

      return detPatfam;
   }

   @Override
   @Transactional(readOnly = true)
   public List<DetPatfam> findDetPatfamByParams(Integer idServicio, Integer idDynamic) {
      List<DetPatfam> detPatfam = this.repository.findDetPatfamByParams(idServicio, idDynamic);
      if (detPatfam.isEmpty()) {
         throw new NotFoundException();
      }

      return detPatfam;
   }

}
