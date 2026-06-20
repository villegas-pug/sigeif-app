package microservice.educalle.anexo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import microservice.educalle.anexo.dtos.UnidadServicioDto;

public class UnidadServicioRowMapper implements RowMapper<UnidadServicioDto> {

    @Override
    public UnidadServicioDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        UnidadServicioDto dto = new UnidadServicioDto();

        dto.setIdServicio(rs.getLong("idServicio"));
        dto.setNombreServicio(rs.getString("nombreServicio"));

        return dto;
    }

}
