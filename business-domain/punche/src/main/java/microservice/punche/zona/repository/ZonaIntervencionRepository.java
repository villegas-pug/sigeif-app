package microservice.punche.zona.repository;

import java.util.List;

import microservice.punche.zona.dtos.ZonaIntervencionResponse;
import microservice.punche.zona.dtos.ZonaIntervencionSaveDto;
import microservice.punche.zona.model.ZonaIntervencion;

public interface ZonaIntervencionRepository {

   public void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion);

   public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion);

   public List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona);

}
