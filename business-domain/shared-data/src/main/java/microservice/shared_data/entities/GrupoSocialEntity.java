package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_GRUPO_SOCIAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idGrupoSocial" })
public class GrupoSocialEntity {

   @Id
   @Column(name = "GR_ID_GRUPO")
   private Long idGrupoSocial;

   @Column(name = "GR_DESCRIPCION")
   private String descripcion;

}