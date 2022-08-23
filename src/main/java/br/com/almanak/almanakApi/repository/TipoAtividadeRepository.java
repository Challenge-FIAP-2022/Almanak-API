package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.TipoAtividade;

@Repository
public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Integer> {
    
    @Query(value="select * from tb_tipo_atividade where lower(nm_grupo) = lower(?1)", nativeQuery = true) 
    Optional<TipoAtividade> findByName(String nome);

    @Query(value="select * from tb_tipo_atividade where lower(nm_grupo) = 'login'", nativeQuery = true) 
    Optional<TipoAtividade> login();

}
