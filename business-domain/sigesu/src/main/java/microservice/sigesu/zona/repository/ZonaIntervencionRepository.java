package microservice.sigesu.zona.repository;

import java.util.List;

import microservice.sigesu.zona.dtos.ZonaIntervencionResponse;
import microservice.sigesu.zona.dtos.ZonaIntervencionSaveDto;
import microservice.sigesu.zona.model.ZonaIntervencion;

public interface ZonaIntervencionRepository {

   public void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion);

   public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion);

   public List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona);

}
