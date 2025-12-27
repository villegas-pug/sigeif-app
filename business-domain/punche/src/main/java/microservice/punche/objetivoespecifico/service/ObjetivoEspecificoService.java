package microservice.punche.objetivoespecifico.service;

import java.util.List;
import microservice.punche.objetivoespecifico.dtos.ObjetivoEspecificoResponse;

public interface ObjetivoEspecificoService {

   List<ObjetivoEspecificoResponse> findAllObjetivosEspecificosByServicio(Long idServicio);

}
