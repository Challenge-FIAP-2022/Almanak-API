package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.PlanoUsuarioRel;

@Repository
public interface PlanousuarioRelRepository extends JpaRepository<PlanoUsuarioRel, Integer>{
    
}
