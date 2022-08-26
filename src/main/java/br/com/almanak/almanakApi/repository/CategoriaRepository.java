package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.almanak.almanakApi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
