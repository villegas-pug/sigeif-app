package microservice.educalle.objetivoespecifico.service;

import java.util.List;
import microservice.educalle.objetivoespecifico.dtos.ObjetivoEspecificoResponse;

public interface ObjetivoEspecificoService {

   List<ObjetivoEspecificoResponse> findAllObjetivosEspecificosByServicio(Long idServicio);

}
