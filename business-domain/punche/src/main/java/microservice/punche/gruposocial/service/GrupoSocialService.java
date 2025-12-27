package microservice.punche.gruposocial.service;

import java.util.List;
import microservice.shared_data.entities.GrupoSocialEntity;

public interface GrupoSocialService {

   List<GrupoSocialEntity> findAllGrupoSocial();

}