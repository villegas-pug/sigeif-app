package microservice.punche.reporting.dtos;

public record CompromisoFamiliarPdfData(
      String nombresCuidador,
      String numDocCuidador,
      String nombresAcompañante,
      String numDocAcompañante,
      String fechaCompromiso) {
}
