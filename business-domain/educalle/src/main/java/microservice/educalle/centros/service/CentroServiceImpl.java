package microservice.educalle.centros.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import microservice.educalle.centros.dto.CentroDTO;
import microservice.educalle.centros.repository.CentroRepository;
import microservice.educalle.centros.service.CentroService;

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