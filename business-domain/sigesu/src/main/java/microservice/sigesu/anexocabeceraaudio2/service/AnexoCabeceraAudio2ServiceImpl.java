package microservice.sigesu.anexocabeceraaudio2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.sigesu.anexocabeceraaudio2.dtos.CreateAnexoCabeceraAudio2Request;
import microservice.sigesu.anexocabeceraaudio2.dtos.UpdateAnexoCabeceraAudio2Request;
import microservice.sigesu.anexocabeceraaudio2.mappers.AnexoCabeceraAudio2CreateMapper;
import microservice.sigesu.anexocabeceraaudio2.mappers.AnexoCabeceraAudio2UpdateMapper;
import microservice.sigesu.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.sigesu.anexocabeceraaudio2.repository.AnexoCabeceraAudio2Repository;

@Service
@AllArgsConstructor
public class AnexoCabeceraAudio2ServiceImpl implements AnexoCabeceraAudio2Service {

	private final AnexoCabeceraAudio2Repository repository;
	private final AnexoCabeceraAudio2CreateMapper createMapper;
	private final AnexoCabeceraAudio2UpdateMapper updateMapper;

	@Override
	@Transactional
	public <M> M createAnexoCabeceraAudio2(CreateAnexoCabeceraAudio2Request request) {
		return (M) this.repository.save(this.createMapper.toModel(request));
	}

	@Override
	@Transactional
	public <M> M updateAnexoCabeceraAudio2(UpdateAnexoCabeceraAudio2Request request) {
		return (M) this.repository.save(this.updateMapper.toModel(request));
	}

	@Override
	public <M> M findAnexoCabeceraAudio2ById(Long idAudio) {
		return this.repository.findAnexoCabeceraAudio2ById(idAudio);
	}

	@Override
	public <M> List<M> findAllAnexoCabeceraAudio2() {
		return this.repository.findAllAnexoCabeceraAudio2();
	}

	@Override
	@Transactional
	public <M> M deleteAnexoCabeceraAudio2ById(Long idAudio) {
		return this.repository.deleteAnexoCabeceraAudio2ById(idAudio);
	}

	@Override
	@Transactional
	public void guardarAudio(byte[] audio, String nombreArchivo, Long idAnexoCabecera) {
		AnexoCabeceraAudio2 model = AnexoCabeceraAudio2.builder()
				.idAnexoCabecera(idAnexoCabecera)
				.audio(audio)
				.nombreArchivo(nombreArchivo)
				.fechaRegistro(LocalDate.now())
				.estado(1)
				.eliminado(0)
				.build();
		this.repository.save(model);
	}

	@Override
	@Transactional
	public void actualizarAudio(Long idAudio, byte[] audio, String nombreArchivo, Integer estado) {
		AnexoCabeceraAudio2 model = AnexoCabeceraAudio2.builder()
				.idAudio(idAudio)
				.audio(audio)
				.nombreArchivo(nombreArchivo)
				.estado(estado)
				.build();
		this.repository.save(model);
	}

	@Override
	public AnexoCabeceraAudio2 obtenerAudioParaDescarga(Long idAudio) {
		return this.repository.findAnexoCabeceraAudio2ById(idAudio);
	}

}
