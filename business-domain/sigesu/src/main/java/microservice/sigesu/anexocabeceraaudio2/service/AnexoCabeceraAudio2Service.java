package microservice.sigesu.anexocabeceraaudio2.service;

import java.util.List;

import microservice.sigesu.anexocabeceraaudio2.dtos.CreateAnexoCabeceraAudio2Request;
import microservice.sigesu.anexocabeceraaudio2.dtos.UpdateAnexoCabeceraAudio2Request;
import microservice.sigesu.anexocabeceraaudio2.model.AnexoCabeceraAudio2;

public interface AnexoCabeceraAudio2Service {

	<M> M createAnexoCabeceraAudio2(CreateAnexoCabeceraAudio2Request request);

	<M> M updateAnexoCabeceraAudio2(UpdateAnexoCabeceraAudio2Request request);

	<M> M findAnexoCabeceraAudio2ById(Long idAudio);

	<M> List<M> findAllAnexoCabeceraAudio2();

	<M> M deleteAnexoCabeceraAudio2ById(Long idAudio);

	void guardarAudio(byte[] audio, String nombreArchivo, Long idAnexoCabecera);

	void actualizarAudio(Long idAudio, byte[] audio, String nombreArchivo, Integer estado);

	AnexoCabeceraAudio2 obtenerAudioParaDescarga(Long idAudio);

}
