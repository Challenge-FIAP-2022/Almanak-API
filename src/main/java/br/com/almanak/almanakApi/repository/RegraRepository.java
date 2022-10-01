package br.com.almanak.almanakApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Regra;

@Repository
public interface RegraRepository extends JpaRepository<Regra, Integer> {

    @Query(value="select * from fn_regra_resposta(?1, ?2)", nativeQuery = true) 
    Optional<List<Regra>> listResponse(Integer id, String jsonString);
    
}
