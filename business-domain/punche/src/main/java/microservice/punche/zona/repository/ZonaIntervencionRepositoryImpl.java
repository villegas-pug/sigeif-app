package microservice.punche.zona.repository;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import lombok.extern.log4j.Log4j2;
import microservice.punche.aliado.model.AliadoDto;
import microservice.punche.equipotrabajo.dtos.EquipoTrabajoDto;
import microservice.punche.zona.dtos.ZonaIntervencionResponse;
import microservice.punche.zona.dtos.ZonaIntervencionSaveDto;
import microservice.punche.zona.mappers.ZonaIntervencionEntityMapper;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.shared_data.entities.ZonaIntervencionEntity;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.repositories.BaseOracleRepository;
import oracle.jdbc.driver.OracleConnection;

@Repository
@Log4j2
public class ZonaIntervencionRepositoryImpl extends BaseOracleRepository implements ZonaIntervencionRepository {

   private final ZonaIntervencionJpaRepository jpaRepository;
   private final ZonaIntervencionEntityMapper mapper;

   public ZonaIntervencionRepositoryImpl(
         JdbcTemplate jdbcTemplate,
         DataSource dataSource,
         ZonaIntervencionJpaRepository repository,
         ZonaIntervencionEntityMapper mapper) {
      super(jdbcTemplate, dataSource);
      this.jpaRepository = repository;
      this.mapper = mapper;
   }

   @Override
   public void saveZonaIntervencion(ZonaIntervencionSaveDto zonaIntervencion) {

      try (OracleConnection conn = this.dataSource.getConnection().unwrap(OracleConnection.class)) {

         // Extrae la información de la zona de intervención del DTO de entrada.
         ZonaIntervencionResponse zona = zonaIntervencion.getZonaIntervencion();

         // Prepara datos para crear 1 estructura de objeto para zona de intervención.
         Object[] zonaInterObject = new Object[] {
               zona.getIdInstitucion(),
               zona.getIdUnidadorg(),
               zona.getIdServicio(),
               zona.getIdUbigeo(),
               zona.getCodTipo(),
               zona.getDescripcion(),
               zona.getIdUsuRegistra()
         };

         // Crea una estructura de objeto Oracle para la zona de intervención.
         Struct zonaIntervStruct = conn.createStruct("O_ZONA_INTERVENCION", zonaInterObject);

         // Obtiene la lista de equipos de trabajo y prepara un array de estructuras de
         // objetos Oracle.
         List<EquipoTrabajoDto> equiposTrabajo = zonaIntervencion.getEquiposTrabajo();
         Struct[] equiposTrabajoStructs = equiposTrabajo.stream()
               .map(equipo -> {
                  try {
                     return conn.createStruct("O_EQUIPO_TRABAJO", new Object[] {
                           equipo.getIdPersonal(),
                           equipo.getIdCargo(),
                           equipo.getIdUsuRegistra()
                     });
                  } catch (SQLException e) {
                     // Maneja cualquier error que ocurra durante la creación de la estructura del
                     // contacto.
                     throw new RuntimeException("Error al crear estructura de equipo de trabajo", e);
                  }
               })
               .toArray(Struct[]::new);

         Array equiposTrabajoArray = conn.createOracleArray("T_EQUIPOS_TRABAJO", equiposTrabajoStructs);

         List<AliadoDto> aliados = zonaIntervencion.getAliados();
         Struct[] aliadosStructs = aliados.stream()
               .map(aliado -> {
                  try {
                     Struct[] contactosStructs = aliado.getContacto().stream()
                           .map(contacto -> {
                              try {
                                 return conn.createStruct("O_CONTACTO", new Object[] {
                                       contacto.getIdDocumento(),
                                       contacto.getIdNacionalidad(),
                                       contacto.getNumeroDoc(),
                                       contacto.getNombres(),
                                       contacto.getPrimerApe(),
                                       contacto.getSegundoApe(),
                                       contacto.getCorreo(),
                                       contacto.getTelefono(),
                                       contacto.getDireccion(),
                                       contacto.getIdUsuRegistra()
                                 });
                              } catch (SQLException e) {
                                 throw new RuntimeException("Error al crear estructura de contacto", e);
                              }
                           })
                           .toArray(Struct[]::new);

                     return conn.createStruct("O_ALIADO", new Object[] {
                           aliado.getIdInstitucion(),
                           aliado.getIdGruposocial(),
                           aliado.getGradoinfluencia(),
                           aliado.getInteresServicio(),
                           aliado.getResultado(),
                           aliado.getPosicion(),
                           aliado.getIdUbigeo(),
                           aliado.getTipoAliado(),
                           aliado.getDireccion(),
                           aliado.getTelefono(),
                           aliado.getCorreo(),
                           aliado.getRepresentante(),
                           aliado.getIdUsuRegistra(),
                           contactosStructs
                     });
                  } catch (SQLException e) {
                     throw new RuntimeException("Error al crear estructura de aliado", e);
                  }
               })
               .toArray(Struct[]::new);

         Array aliadoArrays = conn.createOracleArray("T_ALIADOS", aliadosStructs);

         Map<String, Object> inParams = new HashMap<>();
         inParams.put("p_zona_intervencion", zonaIntervStruct);
         inParams.put("p_equipos_trabajo", equiposTrabajoArray);
         inParams.put("p_aliados", aliadoArrays);

         SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
               .withProcedureName("USP_GUARDAR_ZONA_INTERVENCION");

         jdbcCall.execute(inParams);

      } catch (SQLException e) {
         log.error("Error al guardar: {}", e.getMessage(), e);
         throw new RuntimeException("Error al guardar zona de intervención", e);
      }

   }

   // TODO: Eliminar metodo
   public List<ZonaIntervencionResponse> findZonasIntervencionByDescripcionContaining(String descripcionZona) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_descripcion", descripcionZona);
      inParams.put("p_id_servicio", InabifServices.PUNCHE.getId());
      return super.executeProcedureWithInParams("USP_BUSCAR_ZONA_INTERVENCION_POR_DESCRIPCION", inParams,
            "p_resultado_busqueda", ZonaIntervencionResponse.class);
   }

   @Override
   public void updateZonaIntervencion(ZonaIntervencion zonaIntervencion) {

      ZonaIntervencionEntity updateZonaIntervencion = this.jpaRepository.findById(zonaIntervencion.getIdZona()).get();
      this.mapper.fromModelToEntity(zonaIntervencion, updateZonaIntervencion);
      this.jpaRepository.save(updateZonaIntervencion);
   }

}