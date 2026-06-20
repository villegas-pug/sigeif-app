package microservice.educalle.anexopregunta.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.educalle.anexopregunta.dtos.AnexoPregutasDto;
import microservice.educalle.anexopregunta.repository.AnexoPreguntasRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoPreguntasServiceImpl implements AnexoPreguntasService {

   private final AnexoPreguntasRepository repository;

   @Override
   @Transactional(readOnly = true)
   public List<AnexoPregutasDto> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo) {

      List<AnexoPregutasDto> preguntas = repository.findAllAnexoPregustasByParams(idServicio, anexo, grupo);
      if (preguntas.size() == 0) {
         throw new NotFoundException();
      }

      return preguntas;
   }

   @Override
   @Transactional(readOnly = true)
   public List<AnexoPregutasDto> findAllAnexoPregustasByParams2(Integer idServicio, Integer anexo, Integer grupo) {

      List<AnexoPregutasDto> preguntas = repository.findAllAnexoPregustasByParams2(idServicio, anexo, grupo);

      if (preguntas.isEmpty()) {
         throw new NotFoundException();
      }

      return preguntas;
   }

}