package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import br.com.almanak.almanakApi.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value="select * from tb_item where lower(tp_item) = lower(?1) and lower(nm_item) = lower(?2)", nativeQuery = true) 
    Optional<Item> findByName(EN_Tipo_Item tipo, String nome);
    
}
