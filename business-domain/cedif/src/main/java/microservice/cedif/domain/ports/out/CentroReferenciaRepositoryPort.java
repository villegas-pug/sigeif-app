package microservice.cedif.domain.ports.out;

import java.util.List;

import microservice.cedif.domain.models.Departamento;
import microservice.cedif.domain.models.Distrito;
import microservice.cedif.domain.models.Provincia;
import microservice.cedif.domain.ports.in.centroreferencia.CentroReferenciaCreateCommand;
import microservice.shared_data.entities.DivisionTerritorialEntity;

public interface CentroReferenciaRepositoryPort {

   public List<Departamento> findAllDepartamentos();

   public List<Provincia> findAllProvinciasPorDepartamento(String idUbigeo);

   public List<Distrito> findAllDistritosPorProvincia(String idUbigeo);

   public void saveCentrolReferencia(int tipoReferencia, CentroReferenciaCreateCommand centroRefDto);

   public List<DivisionTerritorialEntity> findDivisionTerritorialByUbigeo(String idUbigeo);

}
