package microservice.punche.pais.service;

import java.util.List;

import microservice.shared_data.entities.PaisEntity;

public interface PaisService {

   List<PaisEntity> findAllPais();

}