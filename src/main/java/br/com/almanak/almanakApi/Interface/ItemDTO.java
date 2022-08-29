package br.com.almanak.almanakApi.Interface;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import br.com.almanak.almanakApi.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Integer id;
    private EN_Tipo_Item tipo;
    private String name;
    
    public ItemDTO convert(Item item){
        
        this.id = item.getId();
        this.tipo = item.getTipo();
        this.name = item.getName();

        return this;
        
    }

    public ItemDTO(EN_Tipo_Item tipo, String name) {
        this.tipo = tipo;
        this.name = name;
    }

}
