package microservice.cedif.domain.ports.in.centroreferencia;

import java.util.List;

import microservice.cedif.domain.models.Departamento;
import microservice.cedif.domain.models.Distrito;
import microservice.cedif.domain.models.Provincia;
import microservice.shared_data.entities.DivisionTerritorialEntity;

public interface CentroReferenciaServicePort {

   List<?> findCentrosReferenciaByTipo(int idTipo);

   List<?> findCentrosReferenciaByParams(int idTipo, Integer anio, Integer mes, String ref);

   List<Departamento> findAllDepartamentos();

   List<Provincia> findAllProvinciasPorDepartamento(String idUbigeo);

   List<Distrito> findAllDistritosPorProvincia(String idUbigeo);

   void saveCentroReferencia(int tipoReferencia, CentroReferenciaCreateCommand centroRefDto);

   DivisionTerritorialEntity findDivisionTerritorialByUbigeo(String idUbigeo);

}
