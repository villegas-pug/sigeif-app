package microservice.punche.centroreferencia.service;

import java.util.List;

import microservice.punche.centroreferencia.dtos.CentroRefereciaCreateRequestDto;
import microservice.shared_data.entities.Departamento;
import microservice.shared_data.entities.Distrito;
import microservice.shared_data.entities.DivisionTerritorialEntity;
import microservice.shared_data.entities.Provincia;

public interface CentroReferenciaService {

   List<?> findCentrosReferenciaByTipo(int idTipo);

   List<?> findCentroReferenciaByNombreContaining(int idTipo, String ref);

   List<Departamento> findAllDepartamentos();

   List<Provincia> findAllProvinciasPorDepartamento(String idUbigeo);

   List<Distrito> findAllDistritosPorProvincia(String idUbigeo);

   void saveCentroReferencia(int tipoReferencia, CentroRefereciaCreateRequestDto centroRefDto);

   DivisionTerritorialEntity findDivisionTerritorialByUbigeo(String idUbigeo);

}
