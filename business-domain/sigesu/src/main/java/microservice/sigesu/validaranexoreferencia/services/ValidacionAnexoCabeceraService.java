package microservice.sigesu.validaranexoreferencia.services;

import java.util.List;

import microservice.sigesu.validaranexoreferencia.models.ValidacionAnexoCabecera;

public interface ValidacionAnexoCabeceraService {

   ValidacionAnexoCabecera createValidacionAnexoCabecera(ValidacionAnexoCabecera validacionAnexoCabecera);

   ValidacionAnexoCabecera updateValidacionAnexoCabeceras(List<ValidacionAnexoCabecera> validacionAnexoCabecera);

}
