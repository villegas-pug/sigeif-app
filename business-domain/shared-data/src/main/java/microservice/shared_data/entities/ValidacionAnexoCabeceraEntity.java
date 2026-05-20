package microservice.shared_data.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_VALIDACION_ANEXO_CABECERA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idValAnexo" })
public class ValidacionAnexoCabeceraEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "VA_ID_VALANEXO ")
   private Long idValAnexo;

   @Column(name = "ID_ANEXO_CABECERA")
   private Integer idAnexoCabecera;

   @Column(name = "ID_PERSONAL")
   private Integer idPersonal;

   @Column(name = "VA_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "VA_ESTADO")
   private Integer estado;

   @Column(name = "VA_ELIMINADO")
   private Integer eliminado;

}