package microservice.punche.reporting.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IntervencionReportingRepository {

    List<Map<String, Object>> executeSesionesListar(LocalDate fechaIni, LocalDate fechaFin);

    List<Map<String, Object>> executeTalleresFamiliasListar(LocalDate fechaIni, LocalDate fechaFin);

}
