package microservice.sigesu.anexo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import microservice.sigesu.anexo.dtos.AnexoSimpleDto;

public class AnexoSimpleRowMapper implements RowMapper<AnexoSimpleDto> {

    @Override
    public AnexoSimpleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnexoSimpleDto dto = new AnexoSimpleDto();
        dto.setIdAnexo(rs.getLong("ID_ANEXO")); // Nombre exacto de la columna
        dto.setNombreAnexo(rs.getString("ANX_NOMBRE")); // Nombre exacto de la columna
        dto.setCodigoAnexo2(rs.getString("ANX_CODIGO2"));
        dto.setReqSupervisados(rs.getInt("ANX_REQ_SUPERVISADOS"));
        return dto;
    }
}
