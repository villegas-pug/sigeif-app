package microservice.educalle.anexocabeceraaudio2.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import microservice.educalle.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.shared_data.entities.AnexoCabeceraAudioEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoCabeceraAudio2EntityMapper {

	void fromModelToEntity(AnexoCabeceraAudio2 source, @MappingTarget AnexoCabeceraAudioEntity target);

	AnexoCabeceraAudioEntity toEntity(AnexoCabeceraAudio2 source);

	AnexoCabeceraAudio2 toModel(AnexoCabeceraAudioEntity source);

	List<AnexoCabeceraAudio2> toModels(List<AnexoCabeceraAudioEntity> source);

}
