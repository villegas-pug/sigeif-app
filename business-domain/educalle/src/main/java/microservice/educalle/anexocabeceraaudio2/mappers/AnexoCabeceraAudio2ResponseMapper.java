package microservice.educalle.anexocabeceraaudio2.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import microservice.educalle.anexocabeceraaudio2.dtos.AnexoCabeceraAudio2Response;
import microservice.educalle.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoCabeceraAudio2ResponseMapper {

	AnexoCabeceraAudio2Response toResponse(AnexoCabeceraAudio2 model);

	List<AnexoCabeceraAudio2Response> toResponses(List<AnexoCabeceraAudio2> models);

}
