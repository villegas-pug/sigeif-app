package microservice.educalle.anexocabeceraaudio2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.shared_data.entities.AnexoCabeceraAudioEntity;

@Repository
public interface AnexoCabeceraAudio2JpaRepository extends JpaRepository<AnexoCabeceraAudioEntity, Long> {

}
