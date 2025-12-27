package microservice.shared_data.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRPERSONAL")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idPersonal" })
public class PersonalEntity {

   @Id
   @Column(name = "IDPERSONAL")
   private Long idPersonal;

   @OneToOne(cascade = CascadeType.MERGE)
   @JoinColumn(name = "PRHPERSONA")
   private PersonaEntity persona;

}