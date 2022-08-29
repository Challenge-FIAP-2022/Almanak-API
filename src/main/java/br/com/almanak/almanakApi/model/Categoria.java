package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name="tb_categoria")
@SequenceGenerator(name="categoria", sequenceName="sq_categoria", allocationSize=1)
public class Categoria {

    @Id
    @Column(name="id_categoria")
    @GeneratedValue(generator="categoria", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="nm_categoria")
    private String name;

    @Column(name="lk_icone")
    private String icone;

    @Column(name="lk_image")
    private String imagem;

    @Column(name="ds_categoria")
    private String desc;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="categoria")
    private List<JogoCategoriaRel> jogos  = new ArrayList<JogoCategoriaRel>();

    public void addToList(JogoCategoriaRel rel){
        rel.setCategoria(this);
        this.jogos.add(rel);
    }

    
    public Categoria(String name, String icone, String imagem, String desc) {
        this.name = name;
        this.icone = icone;
        this.imagem = imagem;
        this.desc = desc;
    }

    public Categoria(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

}
