package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Regra;

@Repository
public interface RegraRepository extends JpaRepository<Regra, Integer> {
    
}
