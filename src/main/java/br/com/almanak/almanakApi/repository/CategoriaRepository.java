package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value="select * from tb_categoria where lower(nm_categoria) = lower(?1)", nativeQuery = true) 
    Optional<Categoria> findByName(String nome);
    
}
