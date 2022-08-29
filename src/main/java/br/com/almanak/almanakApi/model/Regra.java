package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name="tb_regra")
@SequenceGenerator(name="regra", sequenceName="sq_regra", allocationSize=1)
public class Regra {

    @Id
    @Column(name="id_regra")
    @GeneratedValue(generator="regra", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nm_regra")
    private String name;

    @Column(name="nr_regra")
    private Integer posicao;

    @Column(name="ds_regra")
    private String desc;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_opcional")
    private EN_Booleano opcional;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_jogo")
    private Jogo jogo;

    public void addJogo(Jogo jogo){
        jogo.addToList(this);
    }

    public Regra(Integer id, String name, Integer posicao, String desc, EN_Booleano opcional,
            LocalDateTime dtRegistro) {
        this.id = id;
        this.name = name;
        this.posicao = posicao;
        this.desc = desc;
        this.opcional = opcional;
        this.dtRegistro = dtRegistro;
    }

    public Regra(String name, Integer posicao, String desc, EN_Booleano opcional) {
        this.name = name;
        this.posicao = posicao;
        this.desc = desc;
        this.opcional = opcional;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

}
