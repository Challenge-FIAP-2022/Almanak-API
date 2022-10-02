package br.com.almanak.almanakApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    // Filtros para Tela do App

    @Query(value="select * from tb_jogo where lower(nm_jogo) = lower(?1)", nativeQuery = true) 
    Optional<Jogo> findByName(String nome);
    
    @Query(value="select j from Jogo j where j.valido = ?1 order by j.name", nativeQuery = false) 
    Optional<List<Jogo>> listByValid(EN_Booleano flag);

    @Query(value="select * from tb_jogo where fl_idade = ?1 and fl_elite = ?2 and fl_vlaido = 'sim' order by nm_jogo", nativeQuery = true) 
    Optional<List<Jogo>> findAllForUser(EN_Booleano maioridade, EN_Booleano elite);

    @Query(value="select * from fn_jogos_recomendados(?1);", nativeQuery = true) 
    Optional<List<Jogo>> listRecommended(Integer id);

    // Nota

    @Query(value="select fn_nota_jogo(?1, current_date)", nativeQuery = true) 
    Optional<Double> findScore(String name);

    @Query(value="select fn_nota_jogo(?1, current_date)", nativeQuery = true) 
    Optional<Double> findScore(Integer id);

    // Filtros

    @Query(value="select j from Jogo j where j.paraAdultos = ?1 order by j.name", nativeQuery = false) 
    Optional<List<Jogo>> listByAgeLimit(EN_Booleano maioridade);

    @Query(value="select * from tb_jogo where nr_min_jogadores <= ?1 and coalesce(nr_max_jogadores,1000) >= ?1", nativeQuery = true) 
    Optional<List<Jogo>> listByPlayers(Integer jogadores);

    @Query(value="select * from tb_jogo j left join tb_jogo_categoria jc on j.id_jogo = jc.id_jogo left join tb_categoria c on jc.id_categoria = c.id_categoria where lower(c.nm_categoria) = lower(?1)", nativeQuery = true) 
    Optional<List<Jogo>> listByCategoria(String categorias);

    @Query(value="select j from Jogo j join fetch j.categorias cr join fetch cr.categoria c where c.name in (?1)", nativeQuery = false) 
    Optional<List<Jogo>> listByListCategoria(List<String> categorias);

    @Query(value="select * from tb_jogo j left join tb_jogo_item ji on j.id_jogo = ji.id_jogo left join tb_item i on ji.id_item = i.id_item where lower(i.tp_item) = lower(?1)", nativeQuery = true) 
    Optional<List<Jogo>> listByIten(String itens);

    @Query(value="select j from Jogo j join fetch j.itens ir join fetch ir.item i where i.name in (?1)", nativeQuery = false) 
    Optional<List<Jogo>> listByListIten(List<String> itens);

}
