package microservice.shared_data.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_CONTACTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idContacto" })
public class Contacto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "CO_ID_CONTACTO")
   private Long idContacto;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "AL_ID_ALIADO")
   private AliadoEntity aliado;

   @OneToOne
   @JoinColumn(name = "ID_TIPDOC")
   private DocumentoEntity tipoDoc;

   @OneToOne
   @JoinColumn(name = "PA_ID_NACIONALIDAD")
   private PaisEntity nacionalidad;

   @Column(name = "CO_NUMERO_DOC")
   private String numeroDoc;

   @Column(name = "CO_NOMBRES")
   private String nombres;

   @Column(name = "CO_PRIMER_APE")
   private String primerApe;

   @Column(name = "CO_SEGUNDO_APE")
   private String segundoApe;

   @Column(name = "CO_TELEFONO")
   private String telefono;

   @Column(name = "CO_DIRECCION")
   private String direccion;

   @Column(name = "CO_CORREO")
   private String correo;

   @Column(name = "CO_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "CO_FEC_REGISTRA")
   private LocalDate fecRegistra;

   @Column(name = "CO_USU_ACTUALIZA")
   private Integer usuActualiza;

   @Column(name = "CO_FEC_ACTUALIZA")
   private LocalDate fecActualiza;

   @Column(name = "CO_ESTADO")
   private Integer estado;

   @Column(name = "CO_ELIMINADO")
   private Integer eliminado;

   @PrePersist()
   public void prePersist() {
      this.setFecRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
   }

   @PreUpdate()
   public void preUpdate() {
      this.setFecActualiza(LocalDate.now());
   }

}