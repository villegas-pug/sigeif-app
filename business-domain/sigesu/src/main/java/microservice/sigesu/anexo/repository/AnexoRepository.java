package microservice.sigesu.anexo.repository;

import java.util.List;
import microservice.sigesu.anexo.dtos.AnexoDto;
import microservice.sigesu.anexo.dtos.AnexoSimpleDto;
import microservice.sigesu.anexo.dtos.UnidadDto;
import microservice.sigesu.anexo.dtos.UnidadServicioDto;

public interface AnexoRepository {

     List<UnidadDto> listarUnidades();

   List<UnidadServicioDto> listarServiciosPorUnidad(Long idUnidadOrganica);

  List<AnexoSimpleDto> listarAnexosPorServicio(Long idUnidadOrganica, Long idServicio);

 
    List<AnexoDto> listarAnexosPorFiltro(
            Long idUnidadOrganica,
            Long idServicio,
            Long idAnexo);

}
