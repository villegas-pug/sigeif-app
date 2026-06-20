package microservice.punche.zona.service;

import java.util.List;
import microservice.punche.zona.dtos.ZonaIntervencionResponse;
import microservice.punche.zona.dtos.ZonaIntervencionSaveDto;
import microservice.punche.zona.model.ZonaIntervencion;

public interface ZonaIntervencionService {

      void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion);

      public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion);

      List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona);

      List<ZonaIntervencion> findZonasIntervencionByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona);

      ZonaIntervencion findZonaIntervencionById(Long idZona);

      void deleteZonaIntervencionById(Long idZona);

      // ? Short
      List<ZonaIntervencion> findZonasIntervencionShortByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona);

      // ? Minified

      List<ZonaIntervencion> findZonasIntervencionMinifiedByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona);

      ZonaIntervencion findZonaIntervencionMinifiedById(Long idZona);

}
