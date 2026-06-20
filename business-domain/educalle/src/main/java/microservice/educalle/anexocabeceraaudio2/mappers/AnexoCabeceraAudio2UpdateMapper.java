package microservice.educalle.anexocabeceraaudio2.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import microservice.educalle.anexocabeceraaudio2.dtos.UpdateAnexoCabeceraAudio2Request;
import microservice.educalle.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoCabeceraAudio2UpdateMapper {

	AnexoCabeceraAudio2 toModel(UpdateAnexoCabeceraAudio2Request update);

	List<AnexoCabeceraAudio2> toModels(List<UpdateAnexoCabeceraAudio2Request> updates);

}
