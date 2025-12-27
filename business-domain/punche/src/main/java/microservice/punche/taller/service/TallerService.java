package microservice.punche.taller.service;

import java.util.List;

import microservice.punche.taller.model.Taller;

public interface TallerService {

   Taller createTaller(Taller taller);

   Taller updateTaller(Taller taller);

   List<Taller> findAllTallerByIdSesion(Integer idSesion);

}
