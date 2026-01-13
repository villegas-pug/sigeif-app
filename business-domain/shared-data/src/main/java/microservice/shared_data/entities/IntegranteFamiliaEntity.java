package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_FAMILIA_INTEGRANTES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idIntegrante" })
public class IntegranteFamiliaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "FI_ID_INTEGRANTE")
   private Long idIntegrante;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "PF_ID_FAMILIA")
   private PotencialFamiliaEntity familia;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "integrante")
   @JsonIgnoreProperties(value = { "familia", "integrante" })
   private List<CodigoFamiliaEntity> codigoIntegrante;

   @OneToMany(mappedBy = "integrante", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "familia", "integrante" })
   private Set<AnexoRespuestaEntity> anexosRespuestas;

   @OneToOne
   @JoinColumn(name = "CA_ID_TIPDOC")
   private DocumentoEntity tipdoc;

   @OneToOne
   @JoinColumn(name = "CA_ID_GRADO_INST")
   private CatalogoEntity gradoInst;

   @OneToOne
   @JoinColumn(name = "CA_ID_TIPO_SEGURO")
   private CatalogoEntity tipoSeguro;

   @OneToOne
   @JoinColumn(name = "PA_ID_NAC")
   private PaisEntity nacionalidad;

   @OneToOne
   @JoinColumn(name = "PA_ID_PAIS_NACIMIENTO")
   private PaisEntity paisNacimiento;

   @OneToOne
   @JoinColumn(name = "CA_ID_PARENTESCO")
   private CatalogoEntity parentesco;

   @OneToOne
   @JoinColumn(name = "CA_ID_ESTADO_CIVIL")
   private CatalogoEntity estadoCivil;

   @OneToOne
   @JoinColumn(name = "CA_ID_SEXO")
   private CatalogoEntity sexo;

   @OneToOne
   @JoinColumn(name = "CA_ID_IDIOMA")
   private CatalogoEntity idioma;

   @OneToOne
   @JoinColumn(name = "CA_ID_DISCAPACIDAD")
   private CatalogoEntity discapacidad;

   @OneToOne
   @JoinColumn(name = "CA_ID_DERIVADO_POR")
   private CatalogoEntity derivadoPor;

   @OneToOne
   @JoinColumn(name = "CA_ID_SERVICIO_CUIDADOR")
   private CatalogoEntity servicioCuidador;

   @OneToOne
   @JoinColumn(name = "CP_ID_CENTRO_POBLA")
   private CatalogoEntity centroPobla;

   @OneToOne
   @JoinColumn(name = "CA_ID_TIPO_FAMILIA")
   private CatalogoEntity tipoFamilia;

   @OneToOne
   @JoinColumn(name = "CA_ID_ETNIA")
   private CatalogoEntity etnia;

   @OneToOne
   @JoinColumn(name = "CA_ID_LENGUA_MATERNA")
   private CatalogoEntity lenguaMaterna;

   @OneToOne
   @JoinColumn(name = "CA_ID_TIPO_VIVIENDA")
   private CatalogoEntity tipoVivienda;

   @OneToOne
   @JoinColumn(name = "CA_ID_UBICACION_VIVIENDA")
   private CatalogoEntity ubicacionVivienda;

   @Column(name = "FI_CANT_INTEGRANTES")
   private Integer cantIntegrantes;

   @Column(name = "FI_CANT_NNA")
   private Integer cantNNA;

   @Column(name = "CA_TIENE_DISCAPACIDAD")
   private Integer tieneDiscapacidad;

   @OneToOne
   @JoinColumn(name = "CA_ID_OCUPACION")
   private CatalogoEntity ocupacion;

   @Column(name = "FI_NUMERO_DOC")
   private String numeroDoc;

   @Column(name = "FI_NOMBRES")
   private String nombres;

   @Column(name = "FI_PRIMER_APE")
   private String primerApe;

   @Column(name = "FI_SEGUNDO_APE")
   private String segundoApe;

   @Column(name = "FI_APELLIDO_CASADO")
   private String apellidoCasado;

   @Column(name = "FI_FEC_NAC")
   private LocalDate fecNac;

   @Column(name = "FI_EDAD")
   private Integer edad;

   @Column(name = "FI_TELEFONO")
   private String telefono;

   @Column(name = "FI_CORREO")
   private String correo;

   @Column(name = "UBI_ID_DEPARTAMENTO")
   private String idDepartamento;

   @OneToOne
   @JoinColumn(name = "UBI_ID_UBIGEO")
   private UbigeoNombreEntity ubigeo;

   @Column(name = "UBI_ID_PROVINCIA")
   private String idProvincia;

   @Column(name = "UBI_ID_DISTRITO")
   private String idDistrito;

   @Column(name = "FI_DIRECCION")
   private String direccion;

   @Column(name = "FI_REFERENCIA_DOMICILIARIA")
   private String referenciaDomiciliaria;

   @Column(name = "FI_GRADO_SECCION_NNA")
   private String gradoSeccionNNA;

   @Column(name = "FI_ANIO_ANTERIOR_PROMOVIDO")
   private Integer anioAnteriorPromovido;

   @Column(name = "FI_NOMBRE_INSTITUCION_EDUCATIVA")
   private String nombreInstitucionEducativa;

   @Column(name = "FI_PESO")
   private String peso;

   @Column(name = "FI_TALLA")
   private String talla;

   @Column(name = "FI_INGRESOS_SOLES")
   private Double ingresosSoles;

   @Column(name = "FI_GASTOS_SOLES")
   private Double gastosSoles;

   @OneToOne
   @JoinColumn(name = "UBI_ID_UBIGEO_NAC")
   private UbigeoNombreEntity ubigeoNac;

   @Column(name = "UBI_ID_DEPARTAMENTO_NAC")
   private String idDepartamentoNac;

   @Column(name = "UBI_ID_PROVINCIA_NAC")
   private String idProvinciaNac;

   @Column(name = "UBI_ID_DISTRITO_NAC")
   private String idDistritoNac;

   @Column(name = "FI_OBSERVACIONES")
   private String observaciones;

   @Column(name = "FI_DIAGNOSTICO_MEDICO")
   private String diagnosticoMedico;

   @Column(name = "FI_ESTABLECIMIENTO_SALUD")
   private String establecimientoSalud;

   @Column(name = "FI_POR_COSTUMBRES_SE_CONSIDERA")
   private String porCostumbresSeConsidera;

   @Column(name = "FI_SITUACION_LABORAL")
   private String situacionLaboral;

   @Column(name = "FI_TIENE_CERT_MEDICO")
   private String tieneCertMedico;

   @Column(name = "FI_GRADO_DISCAPACIDAD")
   private String gradoDiscapacidad;

   @Column(name = "FI_PERFIL_INGRESO_NNA")
   private String perfilIngresoNna;

   @Column(name = "FI_TIPO_EDUCACION")
   private String tipoEducacion;

   @Column(name = "FI_VICTIMA_INDIRECTA_FEMINICIDIO")
   private String victimaIndirectaFeminicidio;

   @Column(name = "FI_GESTANTE")
   private String gestante;

   @Column(name = "FI_LACTANTE")
   private String lactante;

   @Column(name = "FI_INSCRIPCION_CONADIS")
   private String inscripcionConadis;

   @Column(name = "FI_GRADO_INSTRUCCION")
   private String gradoInstruccion;

   @Column(name = "FI_TIPO_DISCAPACIDAD")
   private String tipoDiscapacidad;

   @Column(name = "FI_CENTRO_POBLADO")
   private String centroPoblado;

   // * Nuevo:
   // --------------------------------------------

   @Column(name = "FI_ALGUN_INTEGRANTE_TIENE_PROBLEMA_SALUD")
   private @Builder.Default Integer algunIntegranteTieneProblemaSalud = 0;

   @Column(name = "FI_VIA_INGRESO_NNA_CEDIF")
   private String viaIngresoNnaCedif;

   @Column(name = "FI_MEDIO_INGRESO_NNA_CEDIF")
   private String medioIngresoNnaCedif;

   @Column(name = "FI_OCUPACION")
   private String descripcionOcupacion;
   // --------------------------------------------

   @Column(name = "FI_CUIDADOR")
   private Integer cuidador;

   @Column(name = "FI_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "FI_FEC_REGISTRA")
   private LocalDate fecRegistra;

   @Column(name = "FI_USU_ACTUALIZA")
   private Integer usuActualiza;

   @Column(name = "FI_FEC_ACTUALIZA")
   private LocalDate fecActualiza;

   @Column(name = "PF_INTEGRANTE_APTO")
   private Integer integranteApto;

   @Column(name = "FI_ESTADO")
   private Integer estado;

   @Column(name = "FI_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      // * 1. Base:
      this.setFecRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
      this.setIntegranteApto(0);

      this.calcularUbigeoDomicilio();
      this.calcularUbigeoNacimiento();
   }

   @PreUpdate
   private void preUpdate() {
      this.setFecActualiza(LocalDate.now());

      this.calcularUbigeoDomicilio();
      this.calcularUbigeoNacimiento();
   }

   public String getCodIntegrante() {
      if (this.codigoIntegrante == null || this.codigoIntegrante.isEmpty()) {
         return null;
      }
      return this.codigoIntegrante
            .stream()
            .max(Comparator.comparing(CodigoFamiliaEntity::getTipoCodigo))
            .map(CodigoFamiliaEntity::getCodigo)
            .orElse(null);
   }

   private void calcularUbigeoDomicilio() { // * Domicilio
      if (this.getIdDepartamento() != null && this.getIdProvincia() != null && this.getIdDistrito() != null) {
         this.setUbigeo(UbigeoNombreEntity.builder().idUbigeo(this.getIdDepartamento().concat(
               this.getIdProvincia()).concat(this.getIdDistrito())).build());
      }
   }

   private void calcularUbigeoNacimiento() { // * Nacimiento
      if (this.getIdDepartamentoNac() != null && this.getIdProvinciaNac() != null && this.getIdDistritoNac() != null) {
         this.setUbigeoNac(UbigeoNombreEntity.builder().idUbigeo(this.getIdDepartamentoNac().concat(
               this.getIdProvinciaNac()).concat(this.getIdDistritoNac())).build());
      }
   }

}
