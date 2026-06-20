package microservice.educalle.reporting.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatfamReportingDto {

   private String codFamilia;
   private String referencia;

}
