package br.com.almanak.almanakApi.Interface;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.JogoItemRel;
import br.com.almanak.almanakApi.service.JogoItemRelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensDTO {

    @JsonIgnore
    @Autowired
    JogoItemRelService jirServiceDTO;

    private Integer id;
    private Integer qtd;
    private EN_Booleano necessario;
    private ItemDTO item;

    public ItensDTO convert(JogoItemRel rel){
        
        this.id = rel.getId();
        this.qtd = rel.getQtd();
        this.necessario = rel.getNecessario();
        this.item =new ItemDTO().convert(rel.getItem());

        return this;
        
    }

    public JogoItemRel build(ItemDTO dto){
        Optional<JogoItemRel> item = jirServiceDTO.getById(id);

        if(item.isEmpty()){
            return item.get();
        }else{
            return null;
        }
            
    }

    
    
}
