package br.com.almanak.almanakApi.Interface;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.JogoItemRel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoItemDTO {

    private Integer id;
    private Integer qtd;
    private EN_Booleano necessario;
    private ItemDTO item;

    public JogoItemDTO convert(JogoItemRel rel){
        
        this.id = rel.getId();
        this.qtd = rel.getQtd();
        this.necessario = rel.getNecessario();
        this.item =new ItemDTO().convert(rel.getItem());

        return this;
        
    }

    public JogoItemDTO(Integer qtd, EN_Booleano necessario, ItemDTO item) {
        this.qtd = qtd;
        this.necessario = necessario;
        this.item = item;
    }

}
