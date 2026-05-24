package microservice.sigesu.anexocabeceraaudio2.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import microservice.sigesu.anexocabeceraaudio2.dtos.CreateAnexoCabeceraAudio2Request;
import microservice.sigesu.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoCabeceraAudio2CreateMapper {

	AnexoCabeceraAudio2 toModel(CreateAnexoCabeceraAudio2Request create);

	List<AnexoCabeceraAudio2> toModels(List<CreateAnexoCabeceraAudio2Request> creates);

}
