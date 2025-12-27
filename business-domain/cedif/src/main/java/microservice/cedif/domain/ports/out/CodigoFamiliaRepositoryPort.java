package microservice.cedif.domain.ports.out;

public interface CodigoFamiliaRepositoryPort {

   String generateCodFamilia(Long idFamilia);

   String generateCodIntegrante(Long idIntegrante);

}
