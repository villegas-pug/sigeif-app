package microservice.educalle.validaranexoreferencia.services;

import java.util.List;

import microservice.educalle.validaranexoreferencia.models.ValidacionAnexoCabecera;

public interface ValidacionAnexoCabeceraService {

   ValidacionAnexoCabecera createValidacionAnexoCabecera(ValidacionAnexoCabecera validacionAnexoCabecera);

   ValidacionAnexoCabecera updateValidacionAnexoCabeceras(List<ValidacionAnexoCabecera> validacionAnexoCabecera);

}
