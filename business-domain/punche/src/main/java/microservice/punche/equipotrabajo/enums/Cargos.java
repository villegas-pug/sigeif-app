package microservice.punche.equipotrabajo.enums;

public enum Cargos {
   ACOMPAÑANTE(5509L, "ACOMPAÑANTE");

   private Long id;
   private String nombre;

   private Cargos(Long id, String nombre) {
      this.id = id;
      this.nombre = nombre;
   }

   public Long getId() {
      return id;
   }

   public String getNombre() {
      return nombre;
   }
}
