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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name="tb_item")
@SequenceGenerator(name="item", sequenceName="sq_item", allocationSize=1)
public class Item {

    @Id
    @Column(name="id_item")
    @GeneratedValue(generator="item", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="tp_item")
    private EN_Tipo_Item tipo;
    
    @Column(name="nm_item")
    private String name;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="item")
    private List<JogoItemRel> jogos  = new ArrayList<JogoItemRel>();

    public void addToList(JogoItemRel rel){
        rel.setItem(this);
        this.jogos.add(rel);
    }

    public Item(EN_Tipo_Item tipo, String name) {
        this.tipo = tipo;
        this.name = name;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

}
