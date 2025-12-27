package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_CODIGOS_FAMILIAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idCodigo" })
public class CodigoFamiliaEntity {

   @Id
   @Column(name = "CF_ID_CODIGO")
   private Long idCodigo;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PF_ID_FAMILIA")
   private PotencialFamiliaEntity familia;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "FI_ID_INTEGRANTE")
   private IntegranteFamiliaEntity integrante;

   @Column(name = "CF_CODIGO")
   private String codigo;

   @Column(name = "CF_TIPO_CODIGO")
   private Integer tipoCodigo;

}
