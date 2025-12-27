package microservice.punche.potencialfamilia.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.punche.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.punche.potencialfamilia.dtos.UpdatePartialPotecialFamiliaRequest;
import microservice.punche.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.punche.potencialfamilia.mappers.PotencialFamiliaMapper;
import microservice.punche.potencialfamilia.mappers.PotencialFamiliaUpdateMapper;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.potencialfamilia.repository.PotencialFamiliaRepository;
import microservice.shared_data.dtos.responses.PotencialFamiliaWithEstadoAnexosResponse;
import microservice.shared_data.exceptions.NotFoundByIdException;

@Service
@AllArgsConstructor
public class PotencialFamiliaServiceImpl implements PotencialFamiliaService {

      private final PotencialFamiliaRepository repository;
      private final PotencialFamiliaMapper mapper;
      private final PotencialFamiliaUpdateMapper updateMapper;

      @Override
      @Transactional
      public void createPotecialFamilia(CreatePotencialFamiliaRequest potencialFamilia) {
            this.repository.savePotecialFamilia(this.mapper.fromCreateRequestToModel(potencialFamilia));
      }

      @Override
      @Transactional(readOnly = true)
      public PotencialFamiliaResponse findPotencialFamiliaById(Long idFamilia) {
            PotencialFamiliaResponse potencialFamilia = this.repository.findPotencialFamiliaById(idFamilia)
                        .orElseThrow(() -> new NotFoundByIdException(idFamilia));
            return potencialFamilia;
      }

      @Override
      @Transactional
      public void updatePotencialFamilia(UpdatePotencialFamiliaRequest potencialFamilia) {
            this.repository
                        .savePotecialFamilia(this.mapper.toModel(potencialFamilia));
      }

      @Override
      @Transactional
      public void deletePotencialFamiliaById(Long idFamilia) {
            this.repository.deletePotencialFamiliaById(idFamilia);
      }

      @Override
      @Transactional(readOnly = true)
      public PotencialFamiliaWithEstadoAnexosResponse findPotencialFamiliaWithEstadoAnexosResponseByIdFamilia(
                  Long idFamilia) {
            PotencialFamiliaResponse potencialFamilia = this.repository.findPotencialFamiliaById(idFamilia).get();

            List<PotencialFamiliaWithEstadoAnexosResponse.EstadoAnexo> estadoAnexos = potencialFamilia
                        .getAnexosRespuestas()
                        .stream()
                        .filter(Objects::nonNull)
                        .filter(anexoRespuesta -> anexoRespuesta.getPregunta() != null)
                        .filter(anexoRespuesta -> anexoRespuesta.getPregunta().getNumGrupo() == 0) // ! Pregunta:
                                                                                                   // `Estado`
                        .map(anexoRespuesta -> PotencialFamiliaWithEstadoAnexosResponse.EstadoAnexo
                                    .builder()
                                    .numAnexo(anexoRespuesta.getPregunta().getNumAnexo())
                                    .estado(anexoRespuesta.getEstado())
                                    .etapa(anexoRespuesta.getFase())
                                    .build())
                        .sorted((a, b) -> a.getNumAnexo().compareTo(b.getNumAnexo()))
                        .toList();

            PotencialFamiliaWithEstadoAnexosResponse potencialFamiliaResponse = PotencialFamiliaWithEstadoAnexosResponse
                        .builder()
                        .idFamilia(potencialFamilia.getIdFamilia())
                        .codFamilia(potencialFamilia.getCodFamilia())
                        .estadoAnexos(estadoAnexos)
                        .build();

            return potencialFamiliaResponse;
      }

      @Override
      @Transactional(readOnly = true)
      public Map<Long, PotencialFamiliaWithEstadoAnexosResponse> findPotencialesFamiliasWithEstadoAnexosResponseByIdsFamilia(
                  Set<Long> idsFamilias) {

            List<PotencialFamiliaResponse> potencialesFamilias = this.repository
                        .findPotencialesFamiliasByIds(idsFamilias);

            return potencialesFamilias
                        .stream()
                        .collect(Collectors.toMap(
                                    PotencialFamiliaResponse::getIdFamilia,
                                    this::extractPotencialFamiliaWithEstadoAnexoOfPotencialFamilia,
                                    (existing, replacement) -> existing));

      }

      @Override
      @Transactional
      public PotencialFamilia partialUpdatePotecialFamilia(UpdatePartialPotecialFamiliaRequest potencialFamilia) {
            return this.repository.partialUpdatePotecialFamilia(this.updateMapper.toModel(potencialFamilia));
      }

      @Override
      @Transactional(readOnly = true)
      public PotencialFamiliaWithEstadoAnexosResponse extractPotencialFamiliaWithEstadoAnexoOfPotencialFamilia(
                  PotencialFamiliaResponse potencialFamilia) {

            List<PotencialFamiliaWithEstadoAnexosResponse.EstadoAnexo> estadoAnexos = potencialFamilia
                        .getAnexosRespuestas()
                        .stream()
                        .filter(Objects::nonNull)
                        .filter(anexoRespuesta -> anexoRespuesta.getPregunta() != null)
                        .filter(anexoRespuesta -> anexoRespuesta.getPregunta().getNumGrupo() == 0) // ! Pregunta:
                                                                                                   // `Estado`
                        .map(anexoRespuesta -> PotencialFamiliaWithEstadoAnexosResponse.EstadoAnexo
                                    .builder()
                                    .numAnexo(anexoRespuesta.getPregunta().getNumAnexo())
                                    .estado(anexoRespuesta.getEstado())
                                    .etapa(anexoRespuesta.getFase())
                                    .build())
                        .sorted((a, b) -> a.getNumAnexo().compareTo(b.getNumAnexo()))
                        .toList();

            return PotencialFamiliaWithEstadoAnexosResponse
                        .builder()
                        .idFamilia(potencialFamilia.getIdFamilia())
                        .codFamilia(potencialFamilia.getCodFamilia())
                        .estadoAnexos(estadoAnexos)
                        .build();

      }

}
