package microservice.educalle.centros.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import microservice.educalle.centros.dto.CentroDTO;
import microservice.shared_data.enums.InabifServices;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CentroRepositoryImpl implements CentroRepository {

        private final JdbcTemplate jdbcTemplate;

        private SimpleJdbcCall simpleJdbcCall;

        @PostConstruct
        public void init() {

                simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                                .withProcedureName("SP_LISTAR_CENTROS_V2")
                                .returningResultSet(
                                                "P_CURSOR",
                                                (rs, rowNum) -> {
                                                        CentroDTO dto = new CentroDTO();
                                                        dto.setIdUnidadOrganica(rs.getLong("IDUNIDADORGANICA"));
                                                        dto.setNombreUnidad(rs.getString("UORNOMBRE"));
                                                        dto.setCentroUbigeo(rs.getInt("CENTROUBIGEO"));
                                                        dto.setDepartamento(rs.getString("DEPARTAMENTO"));
                                                        dto.setProvincia(rs.getString("PROVINCIA"));
                                                        dto.setDistrito(rs.getString("DISTRITO"));
                                                        dto.setIdPersonal(rs.getLong("IDPERSONAL"));
                                                        dto.setRespDirector(rs.getString("DIRECTOR"));
                                                        dto.setTipoCentro(rs.getString("TIPO_CENTRO"));
                                                        return dto;
                                                });
        }

        @Override
        public List<CentroDTO> listarCentros(Long idServicio) {

                Map<String, Object> result = simpleJdbcCall.execute(
                                Map.of("P_ID_SERVICIO", idServicio,
                                                "P_TIPO_CENTRO", "-1",
                                                "P_ID_SERVICIO_PADRE", InabifServices.EDUCALLE.getId()));

                return (List<CentroDTO>) result.get("P_CURSOR");
        }
}