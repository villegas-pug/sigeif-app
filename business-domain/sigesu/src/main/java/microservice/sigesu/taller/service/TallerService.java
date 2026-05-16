package microservice.sigesu.taller.service;

import java.util.List;

import microservice.sigesu.taller.model.Taller;

public interface TallerService {

   Taller createTaller(Taller taller);

   Taller updateTaller(Taller taller);

   List<Taller> findAllTallerByIdSesion(Integer idSesion);

}
