package microservice.educalle.anexo.service;

import java.util.List;
import microservice.educalle.anexo.dtos.AnexoDto;
import microservice.educalle.anexo.dtos.AnexoSimpleDto;
import microservice.educalle.anexo.dtos.UnidadDto;
import microservice.educalle.anexo.dtos.UnidadServicioDto;

public interface AnexoService {

    List<UnidadDto> listarUnidades();

    List<UnidadServicioDto> listarUnidadesServicios(Long idUnidadOrganica);

    List<AnexoSimpleDto> listarAnexosPorServicio(Long idUnidadOrganica, Long idServicio);

    List<AnexoDto> listarAnexosPorFiltro(
            Long idUnidadOrganica,
            Long idServicio,
            Long idAnexo);

}
