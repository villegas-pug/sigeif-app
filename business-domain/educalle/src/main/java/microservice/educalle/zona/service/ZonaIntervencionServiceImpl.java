package microservice.educalle.zona.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.anexorespuesta.repository.AnexoRespuestaRepository;
import microservice.educalle.zona.dtos.ZonaIntervencionResponse;
import microservice.educalle.zona.dtos.ZonaIntervencionSaveDto;
import microservice.educalle.zona.mappers.ZonaIntervencionEntityMapper;
import microservice.educalle.zona.model.ZonaIntervencion;
import microservice.educalle.zona.repository.ZonaIntervencionJpaRepository;
import microservice.educalle.zona.repository.ZonaIntervencionRepository;
import microservice.shared_data.entities.ZonaIntervencionEntity;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.exceptions.NotFoundByIdException;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class ZonaIntervencionServiceImpl implements ZonaIntervencionService {

   private final ZonaIntervencionRepository repository;
   private final ZonaIntervencionJpaRepository jpaRepository;
   private final ZonaIntervencionEntityMapper entityMapper;
   private final AnexoRespuestaRepository anexoRespuestaRepository;

   @Override
   @Transactional
   public void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion) {
      this.repository.saveZonaIntervencion(zonaIntervencion);
   }

   @Override
   @Transactional(readOnly = true)
   public ZonaIntervencion findZonaIntervencionById(Long idZona) {

      ZonaIntervencion zonaIntervencion = this.jpaRepository.findById(idZona).map(this.entityMapper::toModel)
            .orElseThrow(NotFoundException::new);

      // * Estado de fichas registradas por familia
      zonaIntervencion.getPotencialesFamilias()
            .stream()
            .forEach(familia -> {
               familia.setEstadoFichas(this.anexoRespuestaRepository
                     .findEstadosAnexosByParams(familia.getIdFamilia(), null));

               // * Estado de fichas registradas por integrante
               familia.getIntegrantesFamilia().forEach(integrante -> {
                  integrante.setEstadoFichas(this.anexoRespuestaRepository
                        .findEstadosAnexosByParams(null, integrante.getIdIntegrante()));
               });
            });

      return zonaIntervencion;
   }

   @Override
   @Transactional
   public void deleteZonaIntervencionById(Long idZona) {
      ZonaIntervencionEntity deletedZonaIntervencion = this.jpaRepository.findById(idZona)
            .orElseThrow(() -> new NotFoundByIdException(idZona));

      deletedZonaIntervencion.setEliminado(1);
      this.jpaRepository.save(deletedZonaIntervencion);
   }

   @Override
   @Transactional
   public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion) {
      this.repository.updateZonaIntervencion(zonaIntervencion);
   }

   @Override
   @Transactional(readOnly = true)
   public List<ZonaIntervencion> findZonasIntervencionByParams(String descripcionZona, int anioRegistroZona,
         int mesRegistroZona) {

      LocalDate fecIni, fecFin;

      if (mesRegistroZona == -1) {
         fecIni = LocalDate.of(anioRegistroZona, 1, 1);
         fecFin = LocalDate.of(anioRegistroZona, 12, 31);
      } else {
         fecIni = LocalDate.of(anioRegistroZona, mesRegistroZona, 1);
         fecFin = fecIni.plusMonths(1).minusDays(1);
      }

      List<ZonaIntervencion> zonasIntervencion = this.jpaRepository
            .findByDescripcionIgnoreCaseAndFecRegistraBetweenAndServicio(
                  descripcionZona,
                  fecIni,
                  fecFin,
                  InabifServices.PUNCHE.getId())
            .stream()
            .map(this.entityMapper::toModel)
            .map(zonaIntervencion -> {
               zonaIntervencion
                     .getPotencialesFamilias()
                     .forEach(familia -> {
                        // * Estado de fichas registradas por familia
                        familia.setEstadoFichas(this.anexoRespuestaRepository
                              .findEstadosAnexosByParams(familia.getIdFamilia(), null));

                        // * Estado de fichas registradas por integrante
                        familia.getIntegrantesFamilia().forEach(integrante -> {
                           integrante.setEstadoFichas(this.anexoRespuestaRepository
                                 .findEstadosAnexosByParams(null, integrante.getIdIntegrante()));
                        });
                     });
               return zonaIntervencion;
            })
            .toList();

      if (zonasIntervencion.isEmpty()) {
         throw new NotFoundException();
      }

      return zonasIntervencion;

   }

   @Override
   @Transactional(readOnly = true)
   public List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona) {
      List<ZonaIntervencionResponse> zonasIntervencion = this.repository
            .findZonasIntervencionByDescripcionContaining(descripcionZona);
      if (zonasIntervencion.size() == 0) {
         throw new NotFoundException();
      }

      return zonasIntervencion;
   }

}