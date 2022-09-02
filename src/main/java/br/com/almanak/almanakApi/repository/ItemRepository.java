package br.com.almanak.almanakApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import br.com.almanak.almanakApi.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    @Query(value="select distinct tp_item from tb_item order by 1", nativeQuery = true) 
    Optional<List<EN_Tipo_Item>> listDistinctTypes();

    @Query(value="select * from tb_item where lower(nm_item) = lower(?1)", nativeQuery = true) 
    Optional<Item> findByName(String nome);

    @Query(value="select i from Item i where i.tipo = ?1", nativeQuery = false) 
    Optional<List<Item>> listByType(EN_Tipo_Item type);

    
}
