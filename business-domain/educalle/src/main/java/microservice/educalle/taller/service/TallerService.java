package microservice.educalle.taller.service;

import java.util.List;

import microservice.educalle.taller.model.Taller;

public interface TallerService {

   Taller createTaller(Taller taller);

   Taller updateTaller(Taller taller);

   List<Taller> findAllTallerByIdSesion(Integer idSesion);

}
