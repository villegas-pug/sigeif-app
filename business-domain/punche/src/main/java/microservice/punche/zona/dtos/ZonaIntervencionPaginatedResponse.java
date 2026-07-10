package microservice.punche.zona.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.zona.model.ZonaIntervencion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZonaIntervencionPaginatedResponse {

   private List<ZonaIntervencion> items;
   private long totalRows;
   private int page;
   private int rowsPerPage;

}
