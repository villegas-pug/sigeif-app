package microservice.sigesu.centros.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import microservice.sigesu.centros.dto.CentroDTO;
import microservice.sigesu.centros.repository.CentroRepository;
import microservice.sigesu.centros.service.CentroService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CentroServiceImpl implements CentroService {

    private final CentroRepository centroRepository;

    @Override
    public List<CentroDTO> listarCentros(Long idUnidadPadre) {

        return centroRepository.listarCentros(idUnidadPadre);

    }
}