package br.com.almanak.almanakApi.Interface;


import java.util.ArrayList;
import java.util.List;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Categoria;
import br.com.almanak.almanakApi.model.Jogo;
import br.com.almanak.almanakApi.model.JogoCategoriaRel;
import br.com.almanak.almanakApi.model.JogoItemRel;
import br.com.almanak.almanakApi.model.Regra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoDTO {

    private Integer id;
    private String imagem;
    private Integer criador;
    private String name;
    private Double score = 0d;
    private Integer minJogadores;
    private Integer maxJogadores;
    private EN_Booleano paraAdultos;
    private List<CategoriaDTO> categorias = new ArrayList<CategoriaDTO>();
    private List<RegraDTO> regras = new ArrayList<RegraDTO>();
    private List<JogoItemDTO> itens = new ArrayList<JogoItemDTO>();

    public JogoDTO convert(Jogo jogo){

        this.id = jogo.getId();
        this.imagem = jogo.getImagem();
        this.criador = jogo.getUsuario().getId();
        this.name = jogo.getName();
        this.minJogadores = jogo.getMinJogadores();
        this.maxJogadores = jogo.getMaxJogadores();
        this.paraAdultos = jogo.getParaAdultos();
        
        for(JogoCategoriaRel jc : jogo.getCategorias()){
            Categoria categoria = jc.getCategoria();
            categorias.add(new CategoriaDTO().convert(categoria));
        }

        for(Regra r : jogo.getRegras()){
            regras.add(new RegraDTO().convert(r));
        }

        for(JogoItemRel i : jogo.getItens()){
            itens.add(new JogoItemDTO().convert(i));
        }

        return this;
        
    }

    public JogoDTO(Integer criador, String name, Integer minJogadores, Integer maxJogadores, EN_Booleano paraAdultos,
            List<CategoriaDTO> categorias, List<RegraDTO> regras, List<JogoItemDTO> itens) {
        this.criador = criador;
        this.name = name;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.paraAdultos = paraAdultos;
        this.categorias = categorias;
        this.regras = regras;
        this.itens = itens;
    }

    public JogoDTO(Integer criador, String name, Integer minJogadores, List<CategoriaDTO> categorias,
            List<RegraDTO> regras, List<JogoItemDTO> itens) {
        this.criador = criador;
        this.name = name;
        this.minJogadores = minJogadores;
        this.categorias = categorias;
        this.regras = regras;
        this.itens = itens;
    }

}
