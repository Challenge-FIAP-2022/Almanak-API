package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.TipoAtividade;

@Repository
public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Integer> {
    
    @Query(value="select * from tb_tipo_atividade where lower(nm_subgrupo) = lower(?1)", nativeQuery = true) 
    Optional<TipoAtividade> findByName(String nome);

    @Query(value="select * from tb_tipo_atividade where lower(nm_subgrupo) = 'login'", nativeQuery = true) 
    Optional<TipoAtividade> login();

    @Query(value="select * from tb_tipo_atividade where lower(nm_grupo) = 'login' and lower(nm_subgrupo) = 'abrir app'", nativeQuery = true) 
    Optional<TipoAtividade> abrirApp();

    @Query(value="select * from tb_tipo_atividade where lower(nm_grupo) = 'cadastro' and lower(nm_subgrupo) = 'cadastro de cartao'", nativeQuery = true) 
    Optional<TipoAtividade> cadastrarCartao();

}
