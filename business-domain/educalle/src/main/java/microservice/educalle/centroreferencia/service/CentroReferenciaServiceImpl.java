package microservice.educalle.centroreferencia.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.centroreferencia.dtos.CentroRefereciaCreateRequestDto;
import microservice.educalle.centroreferencia.mappers.UnidadOrganicaResponseMapper;
import microservice.educalle.centroreferencia.repository.CentroReferenciaRepository;
import microservice.educalle.institucion.repository.InstitucionRepository;
import microservice.educalle.unidadorganica.repository.UnidadOrganicaRepository;
import microservice.shared_data.entities.Departamento;
import microservice.shared_data.entities.Distrito;
import microservice.shared_data.entities.DivisionTerritorialEntity;
import microservice.shared_data.entities.InstitucionEntity;
import microservice.shared_data.entities.Provincia;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class CentroReferenciaServiceImpl implements CentroReferenciaService {

   private enum UbigeoScope {
      DEPARTAMENTOS,
      PROVINCIAS,
      DISTRITOS
   }

   private final UnidadOrganicaRepository unidadOrganicaRepository;
   private final InstitucionRepository institucionRepository;
   private final CentroReferenciaRepository centroReferenciaRepository;
   private final UnidadOrganicaResponseMapper uoResponseMapper;

   @Override
   @Transactional(readOnly = true)
   public List<?> findCentrosReferenciaByTipo(int idTipo) {

      List<?> centrosReferencia = List.of();

      switch (idTipo) {
         case 1: // CEDIF
            List<UnidadOrganicaEntity> unidadOrganicas = this.unidadOrganicaRepository.findAll();

            centrosReferencia = unidadOrganicas;
            break;
         case 2:

            // INSTITUCION
            List<InstitucionEntity> instituciones = this.institucionRepository.findAll();
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
   public List<?> findCentroReferenciaByNombreContaining(int idTipo, String ref) {

      List<?> centrosReferencia = List.of();
      switch (idTipo) {
         case 1: // CEDIF
            List<UnidadOrganicaEntity> unidadOrganicas = this.unidadOrganicaRepository
                  .findByNombreReferenciaContainingIgnoreCase(ref);

            centrosReferencia = this.uoResponseMapper.fromModels(unidadOrganicas);
            break;
         case 2:

            // INSTITUCION
            List<InstitucionEntity> instituciones = this.institucionRepository
                  .findByNombreReferenciaContainingIgnoreCase(ref);

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
   @Transactional(readOnly = true)
   public List<?> findAllUbigeoByParams(String idDep, String idProv) {
      UbigeoScope scope = idProv != null ? UbigeoScope.DISTRITOS
            : idDep != null ? UbigeoScope.PROVINCIAS
                  : UbigeoScope.DEPARTAMENTOS;

      return switch (scope) {
         case DISTRITOS -> this.findAllDistritosPorProvincia(idProv);
         case PROVINCIAS -> this.findAllProvinciasPorDepartamento(idDep);
         case DEPARTAMENTOS -> this.findAllDepartamentos();
      };
   }

   @Override
   @Transactional
   public void saveCentroReferencia(int tipoReferencia, CentroRefereciaCreateRequestDto centroRefDto) {
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
