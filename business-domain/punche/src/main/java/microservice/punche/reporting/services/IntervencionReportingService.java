package microservice.punche.reporting.services;

import java.time.LocalDate;

public interface IntervencionReportingService {

    byte[] generateSesionesExcelReport(LocalDate fechaIni, LocalDate fechaFin, Long idZona);

    byte[] generateTalleresFamiliasExcelReport(LocalDate fechaIni, LocalDate fechaFin, Long idZona);

}
