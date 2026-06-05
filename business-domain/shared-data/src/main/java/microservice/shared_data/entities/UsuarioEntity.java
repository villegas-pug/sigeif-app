package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TSUSUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idUsuario" })
public class UsuarioEntity {

   @Id
   @Column(name = "IDUSUARIO")
   private Long idUsuario;

   @Column(name = "USULDAP")
   private String login;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "USUPERSONA")
   private PersonaEntity persona;

   @Column(name = "USUESTADO")
   private Integer estado;

}
