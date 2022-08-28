package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_jogo_categoria")
@SequenceGenerator(name="categoriaJogo", sequenceName="sq_jogo_categoria", allocationSize=1)
public class JogoCategoriaRel {

    @Id
    @Column(name="id_jogo_categoria")
    @GeneratedValue(generator="categoriaJogo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="id_jogo")
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    public void addDependencies(Jogo jogo, Categoria categoria){
        jogo.addToList(this);
        categoria.addToList(this);
    }

    public void addJogo(Jogo jogo){
        jogo.addToList(this);
    }

    public void addCategoria(Categoria categoria){
        categoria.addToList(this);
    }

    public JogoCategoriaRel(Jogo jogo, Categoria categoria) {
        this.jogo = jogo;
        this.categoria = categoria;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }
    
}
