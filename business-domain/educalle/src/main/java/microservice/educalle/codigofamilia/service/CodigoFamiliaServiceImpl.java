package microservice.educalle.codigofamilia.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.educalle.codigofamilia.repository.CodigoFamiliaRepository;

@Service
@AllArgsConstructor
public class CodigoFamiliaServiceImpl implements CodigoFamiliaService {

   private final CodigoFamiliaRepository repository;

   @Override
   @Transactional
   public String generateCodFamilia(Long idFamilia) {
      return this.repository.generateCodFamilia(idFamilia);
   }

   @Override
   @Transactional
   public String generateCodIntegrante(Long idIntegrante) {
      return this.repository.generateCodFamilia(idIntegrante);
   }

}
