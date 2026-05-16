package microservice.sigesu.aliado.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.shared_data.entities.AliadoEntity;
import microservice.sigesu.aliado.mappers.AliadoEntityMapper;
import microservice.sigesu.aliado.model.Aliado;

@Repository
@AllArgsConstructor
public class AliadoRepositoryImpl implements AliadoRepository {

   private AliadoJpaRepository jpaRepository;
   private AliadoEntityMapper mapper;

   @Override
   public Aliado saveAliado(Aliado aliado) {

      Long idAliado = aliado.getIdAliado();
      AliadoEntity newAliado = new AliadoEntity();

      if (idAliado != null) { // * Actualiza
         newAliado = this.jpaRepository.findById(idAliado).get();
      }

      // * Común
      this.mapper.fromModelToEntity(aliado, newAliado);

      return this.mapper.toModel(this.jpaRepository.save(newAliado));
   }

   @Override
   public Optional<Aliado> findAliadoById(Long idAliado) {
      return this.jpaRepository.findById(idAliado).map(mapper::toModel);
   }

   @Override
   public void deleteAliadoById(Long idAliado) {
      this.jpaRepository.findById(idAliado).map(oldAliado -> {
         oldAliado.setEliminado(1);
         return oldAliado;
      });
   }

   @Override
   public List<Aliado> findAliadosByIdZona(Long idZona) {
      List<Aliado> aliados = this.jpaRepository.findAliadosByIdZona(idZona).stream().map(this.mapper::toModel).toList();
      return aliados;
   }

}
