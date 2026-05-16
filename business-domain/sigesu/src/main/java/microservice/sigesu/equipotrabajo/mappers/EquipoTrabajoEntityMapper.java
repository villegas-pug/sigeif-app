package microservice.sigesu.equipotrabajo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.sigesu.equipotrabajo.dtos.EquipoTrabajoProjection;
import microservice.sigesu.equipotrabajo.model.EquipoTrabajo;
import microservice.sigesu.persona.model.Persona;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.zona.model.ZonaIntervencion;
import microservice.shared_data.entities.EquipoTrabajoEntity;
import microservice.shared_data.entities.PersonaEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.ZonaIntervencionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EquipoTrabajoEntityMapper {

   // * Mapper's dep's
   @Mappings({
         @Mapping(target = "sexo", ignore = true),
         @Mapping(target = "nombres", ignore = true),
         @Mapping(target = "apePaterno", ignore = true),
         @Mapping(target = "apeMaterno", ignore = true),
         @Mapping(target = "tipoDoc", ignore = true),
         @Mapping(target = "numeroDoc", ignore = true),
         @Mapping(target = "fechaNacimiento", ignore = true),
         @Mapping(target = "direccion", ignore = true),
         @Mapping(target = "telefono", ignore = true),
         @Mapping(target = "correo", ignore = true)
   })
   PersonaEntity toPersonaEntity(Persona source);

   @Mapping(target = "persona", source = "persona", qualifiedByName = "mapPersonalToEntity")
   PersonalEntity toPersonalEntity(Personal source);

   // * Mapper main
   @Mapping(target = "personal", source = "personal") // ! Verifiar: No mapea a Persona
   void fromModelToEntity(EquipoTrabajo source, @MappingTarget EquipoTrabajoEntity target);

   @Mappings({
         @Mapping(target = "zonaIntervencion", ignore = true)
   })
   EquipoTrabajo toModel(EquipoTrabajoEntity source);

   EquipoTrabajo toModel(EquipoTrabajoProjection source);

   // * Method's dep's
   @Named("mapPersonalToEntity")
   default PersonaEntity mapPersonalToEntity(Persona personal) {
      System.out.println(personal);
      return this.toPersonaEntity(personal);
   }

}
