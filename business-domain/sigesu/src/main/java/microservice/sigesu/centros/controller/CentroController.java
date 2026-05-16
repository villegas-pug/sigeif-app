package microservice.sigesu.centros.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import microservice.sigesu.centros.dto.CentroDTO;
import microservice.sigesu.centros.service.CentroService;

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