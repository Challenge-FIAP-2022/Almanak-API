package br.com.almanak.almanakApi.Interface;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Regra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegraDTO {

    private Integer id;
    private String nome;
    private Integer posicao;
    private String desc;
    private EN_Booleano opcional;

    public RegraDTO convert(Regra regra){
        
        this.id = regra.getId();
        this.nome = regra.getName();
        this.posicao = regra.getPosicao();
        this.desc = regra.getDesc();
        this.opcional = regra.getOpcional();

        return this;
        
    }

    public RegraDTO(String nome, Integer posicao, String desc, EN_Booleano opcional) {
        this.nome = nome;
        this.posicao = posicao;
        this.desc = desc;
        this.opcional = opcional;
    }

}
