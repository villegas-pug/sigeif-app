package microservice.sigesu.objetivoespecifico.service;

import java.util.List;
import microservice.sigesu.objetivoespecifico.dtos.ObjetivoEspecificoResponse;

public interface ObjetivoEspecificoService {

   List<ObjetivoEspecificoResponse> findAllObjetivosEspecificosByServicio(Long idServicio);

}
