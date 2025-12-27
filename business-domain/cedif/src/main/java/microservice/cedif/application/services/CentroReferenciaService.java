package microservice.cedif.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Departamento;
import microservice.cedif.domain.models.Distrito;
import microservice.cedif.domain.models.Institucion;
import microservice.cedif.domain.models.Provincia;
import microservice.cedif.domain.models.UnidadOrganica;
import microservice.cedif.domain.ports.in.centroreferencia.CentroReferenciaCreateCommand;
import microservice.cedif.domain.ports.in.centroreferencia.CentroReferenciaServicePort;
import microservice.cedif.domain.ports.out.AnexoRespuestaRepositoryPort;
import microservice.cedif.domain.ports.out.CentroReferenciaRepositoryPort;
import microservice.cedif.domain.ports.out.InstitucionRepositoryPort;
import microservice.cedif.domain.ports.out.UnidadOrganicaRepositoryPort;
import microservice.shared_data.entities.DivisionTerritorialEntity;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class CentroReferenciaService implements CentroReferenciaServicePort {

   private final UnidadOrganicaRepositoryPort unidadOrganicaRepository;
   private final InstitucionRepositoryPort institucionRepository;
   private final CentroReferenciaRepositoryPort centroReferenciaRepository;
   private final AnexoRespuestaRepositoryPort anexoRespuestaRepository;

   @Override
   @Transactional(readOnly = true)
   public List<?> findCentrosReferenciaByTipo(int idTipo) { // ! Método deprecado

      List<?> centrosReferencia = List.of();

      switch (idTipo) {
         case 1: // CEDIF
            List<UnidadOrganica> unidadOrganicas = this.unidadOrganicaRepository.findAllUnidadOrganicas();
            centrosReferencia = unidadOrganicas;
            break;
         case 2:

            // INSTITUCION
            List<Institucion> instituciones = this.institucionRepository.findAllInstituciones();
            centrosReferencia = instituciones;
            break;
         default:
            break;
      }

      if (centrosReferencia.size() == 0) {
         throw new NotFoundException();
      }

      return centrosReferencia;
   }

   @Override
   @Transactional(readOnly = true)
   public List<?> findCentrosReferenciaByParams(int idTipo, Integer anio, Integer mes, String ref) {

      List<?> centrosReferencia = List.of();
      switch (idTipo) {
         case 1: // CEDIF

            List<UnidadOrganica> unidadOrganicas = this.unidadOrganicaRepository
                  .findUnidadesOrganicasByParams(InabifServices.CEDIF.getId(), anio, mes, ref);

            // * Estados de fichas registradas por familia e integrantes
            unidadOrganicas
                  .forEach(unidadOrganica -> {
                     unidadOrganica
                           .getPotencialesFamilias()
                           .forEach(familia -> {

                              // * Familia
                              familia.setEstadoFichas(this.anexoRespuestaRepository
                                    .findEstadosAnexosByParams(familia.getIdFamilia(), null));

                              // * Integrantes
                              familia.getIntegrantesFamilia().forEach(integrante -> {
                                 integrante.setEstadoFichas(this.anexoRespuestaRepository
                                       .findEstadosAnexosByParams(null, integrante.getIdIntegrante()));
                              });

                           });
                  });

            centrosReferencia = unidadOrganicas;
            break;
         case 2:

            // INSTITUCION
            List<Institucion> instituciones = this.institucionRepository
                  .findTop10ByNombreReferenciaContainingIgnoreCase(ref);
            centrosReferencia = instituciones;
            break;

         default:
            break;
      }

      if (centrosReferencia.size() == 0) {
         throw new NotFoundException();
      }

      return centrosReferencia;

   }

   @Override
   @Transactional(readOnly = true)
   public List<Departamento> findAllDepartamentos() {
      List<Departamento> departamentos = this.centroReferenciaRepository.findAllDepartamentos();
      if (departamentos.size() == 0) {
         throw new NotFoundException();
      }
      return departamentos;
   }

   @Override
   @Transactional(readOnly = true)
   public List<Provincia> findAllProvinciasPorDepartamento(String idUbigeo) {
      List<Provincia> provincias = this.centroReferenciaRepository.findAllProvinciasPorDepartamento(idUbigeo);
      if (provincias.size() == 0) {
         throw new NotFoundException();
      }
      return provincias;
   }

   @Override
   @Transactional(readOnly = true)
   public List<Distrito> findAllDistritosPorProvincia(String idUbigeo) {
      List<Distrito> distritos = this.centroReferenciaRepository.findAllDistritosPorProvincia(idUbigeo);
      if (distritos.size() == 0) {
         throw new NotFoundException();
      }
      return distritos;
   }

   @Override
   @Transactional
   public void saveCentroReferencia(int tipoReferencia, CentroReferenciaCreateCommand centroRefDto) {
      this.centroReferenciaRepository.saveCentrolReferencia(tipoReferencia, centroRefDto);
   }

   @Override
   @Transactional(readOnly = true)
   public DivisionTerritorialEntity findDivisionTerritorialByUbigeo(String idUbigeo) {

      List<DivisionTerritorialEntity> divisionReDivisionTerritorial = this.centroReferenciaRepository
            .findDivisionTerritorialByUbigeo(idUbigeo);

      if (divisionReDivisionTerritorial.size() == 0) {
         throw new NotFoundException();
      }

      return divisionReDivisionTerritorial.get(0);
   }

}