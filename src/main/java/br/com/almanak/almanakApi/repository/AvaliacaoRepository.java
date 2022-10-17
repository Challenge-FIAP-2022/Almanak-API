package br.com.almanak.almanakApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    @Query(value="select * from tb_avaliacao where id_jogo = ?1", nativeQuery = true) 
    Optional<List<Avaliacao>> listByGame(Integer id);
    
}
