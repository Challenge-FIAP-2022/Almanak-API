package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{
    
}
