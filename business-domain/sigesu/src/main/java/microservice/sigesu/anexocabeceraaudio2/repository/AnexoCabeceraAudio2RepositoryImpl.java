package microservice.sigesu.anexocabeceraaudio2.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import microservice.sigesu.anexocabeceraaudio2.mappers.AnexoCabeceraAudio2EntityMapper;
import microservice.sigesu.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.shared_data.entities.AnexoCabeceraAudioEntity;

@Repository
@AllArgsConstructor
public class AnexoCabeceraAudio2RepositoryImpl implements AnexoCabeceraAudio2Repository {

	private final AnexoCabeceraAudio2JpaRepository jpaRepository;
	private final AnexoCabeceraAudio2EntityMapper mapper;

	@Override
	public <M> List<M> saveAll(List<AnexoCabeceraAudio2> models) {
		return (List<M>) models.stream().map(this::save).toList();
	}

	@Override
	public <M> M deleteAnexoCabeceraAudio2ById(Long idAudio) {
		AnexoCabeceraAudioEntity entity = this.jpaRepository.findById(idAudio).map(anexo -> {
			anexo.setEliminado(1);
			return anexo;
		}).get();

		return (M) this.mapper.toModel(entity);
	}

	@Override
	public <M> M save(AnexoCabeceraAudio2 model) {
		AnexoCabeceraAudioEntity entity;

		if (model.getIdAudio() == null) {
			entity = new AnexoCabeceraAudioEntity();
		} else {
			entity = this.jpaRepository.findById(model.getIdAudio()).get();
		}

		this.mapper.fromModelToEntity(model, entity);
		this.jpaRepository.save(entity);

		return (M) this.mapper.toModel(entity);
	}

	@Override
	public <M> M findAnexoCabeceraAudio2ById(Long idAudio) {
		return (M) this.jpaRepository.findById(idAudio).map(this.mapper::toModel).get();
	}

	@Override
	public <M> List<M> findAllAnexoCabeceraAudio2() {
		return (List<M>) this.jpaRepository.findAll().stream().map(this.mapper::toModel).toList();
	}

}
