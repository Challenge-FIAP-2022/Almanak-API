package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.almanak.almanakApi.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    
}
