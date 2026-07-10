package microservice.punche.potencialfamilia.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.potencialfamilia.model.PotencialFamilia;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PotencialFamiliaPaginatedResponse {

   private List<PotencialFamilia> items;
   private long totalRows;
   private int page;
   private int rowsPerPage;

}
