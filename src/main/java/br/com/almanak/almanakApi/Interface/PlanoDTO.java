package br.com.almanak.almanakApi.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.service.PlanoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {

    @JsonIgnore
    @Autowired
    PlanoService pServiceDTO;

    private Integer id;
    private String name;
    private String desc;
    private Double valor;

    public PlanoDTO convert(Plano plano){
        try{
            this.id = plano.getId();
            this.name = plano.getName();
            this.desc = plano.getDesc();
            this.valor = plano.getValor();
            return this;
        }catch(Exception e){
            return this;
        }
        
    }

    public List<PlanoDTO> convertList(List<Plano> planos){
        try{
            List<PlanoDTO> dtos = new ArrayList<PlanoDTO>();
            for (Plano p : planos){
                dtos.add(new PlanoDTO().convert(p));
            }
            return dtos;
        }catch(Exception e){
            return new ArrayList<PlanoDTO>();
        }
        
    }

    public Plano build(PlanoDTO dto){
        Optional<Plano> item = pServiceDTO.getById(id);

        if(item.isEmpty()){
            return item.get();
        }else{
            return null;
        }
            
    }
    
}
