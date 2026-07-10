package microservice.punche.zona.service;

import java.util.List;
import microservice.punche.zona.dtos.ZonaIntervencionPaginatedResponse;
import microservice.punche.zona.dtos.ZonaIntervencionResponse;
import microservice.punche.zona.dtos.ZonaIntervencionSaveDto;
import microservice.punche.zona.model.ZonaIntervencion;

public interface ZonaIntervencionService {

      void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion);

      public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion);

      List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona);

      List<ZonaIntervencion> findZonasIntervencionByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona);

      ZonaIntervencionPaginatedResponse findZonasIntervencionByParamsPaginated(String descripcionZona,
                  int anioRegistroZona, int mesRegistroZona, String codFamilia, int page, int rowsPerPage);

      ZonaIntervencion findZonaIntervencionById(Long idZona);

      void deleteZonaIntervencionById(Long idZona);

      // ? Short
      List<ZonaIntervencion> findZonasIntervencionShortByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona); // * Patfam

      List<ZonaIntervencion> findZonasIntervencionToEjecSesionesByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona); // * Ejeccución Sesiones

      // ? Minified

      List<ZonaIntervencion> findZonasIntervencionMinifiedByParams(String descripcionZona, int anioRegistroZona,
                  int mesRegistroZona);

      ZonaIntervencion findZonaIntervencionMinifiedById(Long idZona);

}
