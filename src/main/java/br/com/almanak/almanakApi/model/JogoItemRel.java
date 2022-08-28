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

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_jogo_item")
@SequenceGenerator(name="jogoItem", sequenceName="sq_jogo_item", allocationSize=1)
public class JogoItemRel {

    @Id
    @Column(name="id_jogo_item")
    @GeneratedValue(generator="jogoItem", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="qt_item")
    private Integer qtd;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_necessario")
    private EN_Booleano necessario;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @ManyToOne
    @JoinColumn(name="id_jogo")
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name="id_item")
    private Item item;

    public void addDependencies(Jogo jogo, Item item){
        jogo.addToList(this);
        item.addToList(this);
    }

    public void addJogo(Jogo jogo){
        jogo.addToList(this);
    }

    public void addItem(Item item){
        item.addToList(this);
    }

    public JogoItemRel(Integer id, Integer qtd, EN_Booleano necessario, LocalDateTime dtRegistro) {
        this.id = id;
        this.qtd = qtd;
        this.necessario = necessario;
        this.dtRegistro = dtRegistro;
    }

    public JogoItemRel(Integer qtd, EN_Booleano necessario) {
        this.qtd = qtd;
        this.necessario = necessario;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }
    
}
