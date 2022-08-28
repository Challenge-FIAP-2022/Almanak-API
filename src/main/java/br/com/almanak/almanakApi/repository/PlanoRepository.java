package br.com.almanak.almanakApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer>{

    @Query(value="select p from Plano p where p.valido = ?1 order by p.valor", nativeQuery = false) 
    Optional<List<Plano>> listByValid(EN_Booleano flag);

    @Query(value="select * from tb_plano where lower(nm_plano) = lower(?1)", nativeQuery = true) 
    Optional<Plano> findByName(String nome);
    
}
