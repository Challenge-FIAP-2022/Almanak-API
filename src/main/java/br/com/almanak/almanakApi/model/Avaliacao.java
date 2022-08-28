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
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name="tb_avaliacao")
@SequenceGenerator(name="avaliacao", sequenceName="sq_avaliacao", allocationSize=1)
public class Avaliacao {

    @Id
    @Column(name="id_avaliacao")
    @GeneratedValue(generator="avaliacao", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="vl_avaliacao")
    private Double nota;

    @Column(name="ds_avaliacao")
    private String desc;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_jogo")
    private Jogo jogo;

    public void addDependencies(Usuario usuario, Jogo jogo){
        usuario.addToList(this);
        jogo.addToList(this);
    }

    public void addUsuario(Usuario usuario){
        usuario.addToList(this);
    }

    public void addJogo(Jogo jogo){
        jogo.addToList(this);
    }

    public Avaliacao(Double nota, String desc, Usuario usuario, Jogo jogo) {
        this.nota = nota;
        this.desc = desc;
        this.usuario = usuario;
        this.jogo = jogo;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

}
