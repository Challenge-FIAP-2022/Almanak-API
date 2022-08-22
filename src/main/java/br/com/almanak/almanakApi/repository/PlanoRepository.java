package br.com.almanak.almanakApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer>{

    @Query(value="select * from tb_plano where fl_valido = ?1", nativeQuery = true) 
    List<Plano> listByValid(EN_Booleano flag);
    
}
