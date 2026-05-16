package microservice.sigesu.anexo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.sigesu.anexo.dtos.AnexoDto;
import microservice.sigesu.anexo.dtos.AnexoSimpleDto;
import microservice.sigesu.anexo.dtos.UnidadDto;
import microservice.sigesu.anexo.dtos.UnidadServicioDto;
import microservice.sigesu.anexo.repository.AnexoRepositoryImpl;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoServiceImpl implements AnexoService {

    private final AnexoRepositoryImpl anexoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AnexoDto> listarAnexosPorFiltro(
            Long idUnidadOrganica,
            Long idServicio,
            Long idAnexo) {

        List<AnexoDto> anexos = anexoRepository.listarAnexosPorFiltro(
                idUnidadOrganica,
                idServicio,
                idAnexo);

        if (anexos == null || anexos.isEmpty()) {
            throw new NotFoundException();
        }

        return anexos;

    }

    @Override
    @Transactional(readOnly = true)
    public List<UnidadDto> listarUnidades() {

        List<UnidadDto> unidades = anexoRepository.listarUnidades();

        if (unidades == null || unidades.isEmpty()) {
            throw new NotFoundException();
        }

        return unidades;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UnidadServicioDto> listarUnidadesServicios(Long idUnidadOrganica) {

        List<UnidadServicioDto> lista = anexoRepository.listarServiciosPorUnidad(idUnidadOrganica);

        if (lista == null || lista.isEmpty()) {
            throw new NotFoundException();
        }

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnexoSimpleDto> listarAnexosPorServicio(Long idUnidadOrganica, Long idServicio) {

        List<AnexoSimpleDto> anexos = anexoRepository.listarAnexosPorServicio(idUnidadOrganica, idServicio);

        if (anexos == null || anexos.isEmpty()) {
            throw new NotFoundException();
        }

        return anexos;
    }

}
