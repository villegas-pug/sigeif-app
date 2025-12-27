package microservice.punche.aliado.service;

import java.util.List;

import microservice.punche.aliado.model.Aliado;

public interface AliadoService {

   Aliado createAliado(Aliado aliado);

   Aliado findAliadoById(Long idAliado);

   Aliado updateAliado(Aliado aliado);

   void deleteAliadoById(Long idAliado);

   List<Aliado> findAliadosByIdZona(Long idZona);

}
