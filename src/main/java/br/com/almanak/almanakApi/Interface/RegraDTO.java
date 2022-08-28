package br.com.almanak.almanakApi.Interface;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Regra;
import br.com.almanak.almanakApi.service.RegraService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegraDTO {

    @JsonIgnore
    @Autowired
    RegraService rServiceDTO;

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

    public Regra build(RegraDTO dto){
        Optional<Regra> item = rServiceDTO.getById(id);

        if(item.isEmpty()){
            return item.get();
        }else{
            return null;
        }
            
    }

}
