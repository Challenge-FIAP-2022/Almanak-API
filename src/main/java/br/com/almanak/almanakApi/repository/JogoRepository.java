package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.almanak.almanakApi.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    @Query(value="select j from Jogo j JOIN FETCH j.avaliacoes where j.id = ?1", nativeQuery = false) 
    Optional<Jogo> fetchAvaliacoes(Integer id);
    
}
