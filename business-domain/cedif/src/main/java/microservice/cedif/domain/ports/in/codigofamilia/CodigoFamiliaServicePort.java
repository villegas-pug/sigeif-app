package microservice.cedif.domain.ports.in.codigofamilia;

public interface CodigoFamiliaServicePort {

   String generateCodFamilia(Long idFamilia);

   String generateCodIntegrante(Long idIntegrante);

}
