package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.almanak.almanakApi.model.Regra;

public interface RegraRepository extends JpaRepository<Regra, Integer> {
    
}
