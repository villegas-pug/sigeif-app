package microservice.educalle.centros.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import microservice.educalle.centros.dto.CentroDTO;
import microservice.educalle.centros.service.CentroService;

import java.util.List;

@RestController
@RequestMapping("/centros")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class CentroController {

    private final CentroService centroService;

    @GetMapping("/listar")
    public List<CentroDTO> listarCentros(
            @RequestParam Long idServicio) {
        return centroService.listarCentros(idServicio);
    }
}