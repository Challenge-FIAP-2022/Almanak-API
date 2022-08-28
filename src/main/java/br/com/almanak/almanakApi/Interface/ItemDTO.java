package br.com.almanak.almanakApi.Interface;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import br.com.almanak.almanakApi.model.Item;
import br.com.almanak.almanakApi.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @JsonIgnore
    @Autowired
    ItemService iServiceDTO;

    private Integer id;
    private EN_Tipo_Item tipo;
    private String name;
    
    public ItemDTO convert(Item item){
        
        this.tipo = item.getTipo();
        this.tipo = item.getTipo();
        this.name = item.getName();

        return this;
        
    }

    public Item build(ItemDTO dto){
        Optional<Item> item = iServiceDTO.findByName(dto.getTipo(), dto.getName());

        if(item.isEmpty()){
            return item.get();
        }else{
            return null;
        }
            
    }
    
}
