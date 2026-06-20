package microservice.educalle.zona.repository;

import java.util.List;

import microservice.educalle.zona.dtos.ZonaIntervencionResponse;
import microservice.educalle.zona.dtos.ZonaIntervencionSaveDto;
import microservice.educalle.zona.model.ZonaIntervencion;

public interface ZonaIntervencionRepository {

   public void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion);

   public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion);

   public List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona);

}
