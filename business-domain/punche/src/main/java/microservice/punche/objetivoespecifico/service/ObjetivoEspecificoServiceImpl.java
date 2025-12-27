package microservice.punche.objetivoespecifico.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.objetivoespecifico.dtos.ObjetivoEspecificoResponse;
import microservice.punche.objetivoespecifico.mappers.ObjetivoEspecificoResponseMapper;
import microservice.punche.objetivoespecifico.repository.ObjetivoEspecificoRepository;

@Service
@AllArgsConstructor
public class ObjetivoEspecificoServiceImpl implements ObjetivoEspecificoService {

   private final ObjetivoEspecificoRepository repository;
   private final ObjetivoEspecificoResponseMapper responseMapper;

   @Override
   @Transactional(readOnly = true)
   public List<ObjetivoEspecificoResponse> findAllObjetivosEspecificosByServicio(Long idServicio) {
      return this.repository.findAllObjetivosEspecificos()
            .stream()
            // TODO: El `Id` debe cambiarse según servicio
            .filter(objetivo -> objetivo.getServicio().getIdServicio() == idServicio)
            .map(this.responseMapper::toResponse)
            .toList();
   }

}
