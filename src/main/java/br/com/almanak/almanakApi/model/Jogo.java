package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_jogo")
@SequenceGenerator(name="jogo", sequenceName="sq_jogo", allocationSize=1)
public class Jogo {

    @Id
    @Column(name="id_jogo")
    @GeneratedValue(generator="jogo", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(max=50)
    @Column(name="nm_jogo")
    private String name;

    @Size(max=50)
    @Column(name="lk_imagem")
    private String link;

    @Column(name="nr_min_jogadores")
    private Integer minJogadores;

    @Column(name="nr_max_jogadores")
    private Integer maxJogadores;
    
    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_idade")
    private EN_Booleano paraAdultos;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_valido")
    private EN_Booleano valido;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_elite")
    private EN_Booleano elite;

    @Column(name="ds_encerramento")
    private String descEncerramento;

    @Column(name="dt_encerramento")
    private LocalDateTime dtEncerramento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @Transient
    private Double nota;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="jogo")
    private List<JogoCategoriaRel> categorias  = new ArrayList<JogoCategoriaRel>();;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jogo")
    private List<Regra> regras = new ArrayList<Regra>();;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jogo")
    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="jogo")
    private List<JogoItemRel> itens  = new ArrayList<JogoItemRel>();

    public void addUsuario(Usuario usuario){
        usuario.addToList(this);
    }

    public void addToList(JogoCategoriaRel rel){
        rel.setJogo(this);
        this.categorias.add(rel);
    }

    public void addToList(Avaliacao avaliacao){
        avaliacao.setJogo(this);
        this.avaliacoes.add(avaliacao);
    }

    public void addToList(Regra regra){
        regra.setJogo(this);
        this.regras.add(regra);
    }

    public void addToList(JogoItemRel item){
        item.setJogo(this);
        this.itens.add(item);
    }

    public Jogo(Integer id, @Size(max = 50) String name, Integer minJogadores, Integer maxJogadores,
            EN_Booleano paraAdultos, EN_Booleano valido, EN_Booleano elite, String descEncerramento,
            LocalDateTime dtEncerramento, LocalDateTime dtRegistro, Usuario usuario,
            List<JogoCategoriaRel> categorias) {
        this.id = id;
        this.name = name;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.paraAdultos = paraAdultos;
        this.valido = valido;
        this.elite = elite;
        this.descEncerramento = descEncerramento;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
        this.usuario = usuario;
        this.categorias = categorias;
    }

    public Jogo(Integer id, @Size(max = 50) String name, Integer minJogadores, Integer maxJogadores,
            EN_Booleano paraAdultos, EN_Booleano valido, EN_Booleano elite, String descEncerramento,
            LocalDateTime dtEncerramento, LocalDateTime dtRegistro) {
        this.id = id;
        this.name = name;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.paraAdultos = paraAdultos;
        this.valido = valido;
        this.elite = elite;
        this.descEncerramento = descEncerramento;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
    }

    public Jogo(@Size(max = 50) String name, Integer minJogadores, Integer maxJogadores, EN_Booleano paraAdultos,
            EN_Booleano valido, EN_Booleano elite) {
        this.name = name;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.paraAdultos = paraAdultos;
        this.valido = valido;
        this.elite = elite;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null){
            this.dtRegistro = LocalDateTime.now();
            this.valido = EN_Booleano.sim;
        }
    }

    public void setDtEncerramento() {
        if (this.dtEncerramento == null){
            this.dtEncerramento = LocalDateTime.now();
            this.valido = EN_Booleano.nao;
        }
    }

    public List<String> getNomeCategorias(){
        List<String> nomes = new ArrayList<String>();

        for(JogoCategoriaRel rel : categorias){
            nomes.add(rel.getCategoria().getName());
        }

        return nomes;
    }
    
}
