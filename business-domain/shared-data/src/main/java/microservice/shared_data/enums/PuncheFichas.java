package microservice.shared_data.enums;

public enum PuncheFichas {

   DIAGNOSTICO_FAMILIAR(9L, "DIAGNÓSTICO FAMILIAR"),
   FFSIL(10L, "FICHA DE FUNCIONAMIENTO FAMILIAR SEGÚN INSTRUMENTO FF-SIL"),
   TSV(11L, "ENCUESTA TSV"),
   CUESTIONARIO_SATISFACCION(27L, "CUESTIONARIO SATISFACCIÓN");

   private final Long id;
   private final String nombre;

   PuncheFichas(Long id, String nombre) {
      this.id = id;
      this.nombre = nombre;
   }

   public Long getId() {
      return this.id;
   }

   public String getNombre() {
      return this.nombre;
   }

}
