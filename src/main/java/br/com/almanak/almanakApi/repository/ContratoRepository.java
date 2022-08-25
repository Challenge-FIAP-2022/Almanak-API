package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{

    @Query(value="select c from Plano p where p.valido = 'sim' and p.usuario.id = ?1", nativeQuery = false) 
    Optional<Contrato> findByUser(Integer id);
    
}
