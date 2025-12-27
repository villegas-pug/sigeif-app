package microservice.cedif.infrastructure.adapters.out.persistences.unidadorganica;

import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.UbigeoNombreEntity;

public interface UnidadOrganicaProjection {

   Long getIdUO();

   String getNombreReferencia();

   String getTelefono();

   String getDireccion();

   String getReferencia();

   String getCorreo();

   UbigeoNombreEntity getUbigeo(); // INSCONTACTO

   PersonalEntity getRepresentante();

}
