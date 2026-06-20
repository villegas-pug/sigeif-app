package microservice.educalle.gruposocial.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.educalle.gruposocial.repository.GrupoSocialRepository;
import microservice.shared_data.entities.GrupoSocialEntity;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class GrupoSocialServiceImpl implements GrupoSocialService {

   private final GrupoSocialRepository repository;

   @Override
   @Transactional(readOnly = true)
   public List<GrupoSocialEntity> findAllGrupoSocial() {
      List<GrupoSocialEntity> grupos = this.repository.findAll();
      if (grupos.size() == 0) {
         throw new NotFoundException();
      }
      return grupos;
   }

}