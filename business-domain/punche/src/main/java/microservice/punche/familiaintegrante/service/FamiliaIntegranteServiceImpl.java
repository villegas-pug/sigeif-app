package microservice.punche.familiaintegrante.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.familiaintegrante.dtos.CreateFamiliaIntegranteRequest;
import microservice.punche.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;
import microservice.punche.familiaintegrante.mappers.FamiliaIntegranteCreateMapper;
import microservice.punche.familiaintegrante.mappers.FamiliaIntegranteUpdateMapper;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.familiaintegrante.repository.IntegranteFamiliaRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class FamiliaIntegranteServiceImpl implements IntegranteFamiliaService {

   private final IntegranteFamiliaRepository repository;
   private final FamiliaIntegranteCreateMapper createMapper;
   private final FamiliaIntegranteUpdateMapper updateMapper;

   @Override
   @Transactional
   public <M> List<M> updateIntegrantesFamilia(List<UpdateFamiliaIntegranteRequest> integrantesFamilia) {
      List<FamiliaIntegrante> integrantes = this.repository.saveAll(this.updateMapper.toModels(integrantesFamilia));

      if (integrantes.size() == 0) {// TODO: Crear excepcion ...
         throw new NotFoundException();
      }

      return (List<M>) integrantes;
   }

   @Override
   @Transactional
   public <M> M deleteFamiliaIntegranteById(Long idIntegrante) {
      M model = this.repository.deleteFamiliaIntegranteById(idIntegrante);
      return (M) model;
   }

   @Override
   @Transactional
   public <M> M createIntegranteFamilia(CreateFamiliaIntegranteRequest integranteFamilia) {
      return (M) this.repository.save(this.createMapper.toModel(integranteFamilia));
   }

   @Override
   @Transactional
   public <M> M updateIntegranteFamilia(UpdateFamiliaIntegranteRequest integranteFamilia) {
      return (M) this.repository.save(this.updateMapper.toModel(integranteFamilia));
   }

   @Override
   public <M> M findFamiliaIntegranteById(Long idIntegrante) {
      return this.repository.findFamiliaIntegranteById(idIntegrante);
   }
}
