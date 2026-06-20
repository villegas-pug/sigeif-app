package microservice.educalle.anexo.repository;

import java.util.List;
import microservice.educalle.anexo.dtos.AnexoDto;
import microservice.educalle.anexo.dtos.AnexoSimpleDto;
import microservice.educalle.anexo.dtos.UnidadDto;
import microservice.educalle.anexo.dtos.UnidadServicioDto;

public interface AnexoRepository {

     List<UnidadDto> listarUnidades();

   List<UnidadServicioDto> listarServiciosPorUnidad(Long idUnidadOrganica);

  List<AnexoSimpleDto> listarAnexosPorServicio(Long idUnidadOrganica, Long idServicio);

 
    List<AnexoDto> listarAnexosPorFiltro(
            Long idUnidadOrganica,
            Long idServicio,
            Long idAnexo);

}
