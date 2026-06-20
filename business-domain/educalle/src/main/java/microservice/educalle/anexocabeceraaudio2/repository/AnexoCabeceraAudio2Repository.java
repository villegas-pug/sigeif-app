package microservice.educalle.anexocabeceraaudio2.repository;

import java.util.List;

import microservice.educalle.anexocabeceraaudio2.model.AnexoCabeceraAudio2;

public interface AnexoCabeceraAudio2Repository {

	<M> M save(AnexoCabeceraAudio2 model);

	<M> List<M> saveAll(List<AnexoCabeceraAudio2> models);

	<M> M findAnexoCabeceraAudio2ById(Long idAudio);

	<M> List<M> findAllAnexoCabeceraAudio2();

	<M> M deleteAnexoCabeceraAudio2ById(Long idAudio);

}
