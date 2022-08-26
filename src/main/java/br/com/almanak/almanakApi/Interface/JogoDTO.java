package br.com.almanak.almanakApi.Interface;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.model.Jogo;
import br.com.almanak.almanakApi.model.JogoItemRel;
import br.com.almanak.almanakApi.model.Regra;
import br.com.almanak.almanakApi.service.JogoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoDTO {

    @JsonIgnore
    @Autowired
    JogoService jServiceDTO;

    private Integer id;
    private String name;
    private Double nota = 0d;
    private List<String> categorias = new ArrayList<String>();
    private List<RegraDTO> regras = new ArrayList<RegraDTO>();
    private List<ItensDTO> itens = new ArrayList<ItensDTO>();

    public JogoDTO convert(Jogo jogo){
        List<RegraDTO> regras = new ArrayList<RegraDTO>();
        List<ItensDTO> itens = new ArrayList<ItensDTO>();
        
        this.id = jogo.getId();
        this.name = jogo.getName();
        this.nota = jogo.getNota();
        this.categorias = jogo.getNomeCategorias();

        for(Regra r : jogo.getRegras()){
            regras.add(new RegraDTO().convert(r));
        }
        this.regras = regras;

        for(JogoItemRel i : jogo.getItens()){
            itens.add(new ItensDTO().convert(i));
        }
        this.itens = itens;

        return this;
        
    }

    public List<JogoDTO> convertList(List<Jogo> planos){
        try{
            List<JogoDTO> dtos = new ArrayList<JogoDTO>();
            for (Jogo j : planos){
                dtos.add(new JogoDTO().convert(j));
            }
            return dtos;
        }catch(Exception e){
            return new ArrayList<JogoDTO>();
        }
        
    }

    public Jogo build(ItemDTO dto){
        Optional<Jogo> jogo = jServiceDTO.getById(id);

        if(jogo.isEmpty()){
            return jogo.get();
        }else{
            return null;
        }
            
    }

}
