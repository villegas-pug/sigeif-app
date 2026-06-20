package microservice.educalle.zona.service;

import java.util.List;
import microservice.educalle.zona.dtos.ZonaIntervencionResponse;
import microservice.educalle.zona.dtos.ZonaIntervencionSaveDto;
import microservice.educalle.zona.model.ZonaIntervencion;

public interface ZonaIntervencionService {

      void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion);

      public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion);

      List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona);

      List<ZonaIntervencion> findZonasIntervencionByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona);

      ZonaIntervencion findZonaIntervencionById(Long idZona);

      void deleteZonaIntervencionById(Long idZona);

}
