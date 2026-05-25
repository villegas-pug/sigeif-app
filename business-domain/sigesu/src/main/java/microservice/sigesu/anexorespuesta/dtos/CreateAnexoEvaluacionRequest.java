package microservice.sigesu.anexorespuesta.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateAnexoEvaluacionRequest {

    // CABECERA
    @NotNull
    private Long idAnexo;

    @NotNull
    private Long idCentro;

    // @NotNull
    private Integer usuRegistra;

    private LocalDate fechaAplicacion;

    private LocalDate fechaRegistro;

    // DETALLE
    @Valid
    @NotNull
    private List<CreateAnexoRespuestaRequest> respuestas;

    private Integer idRespSupervision;
    private Integer idDirector;
    // private Long idSupervisado;
    private String idSupervisado;

    // ? Nuevo
    @NotBlank
    private String periodo;

    @NotBlank
    private String tipo;
    private Integer acreditacionVigente;
    private LocalDate fechaAcreditacion;
    private String modalidad;

}