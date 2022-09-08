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
@Table(name="tb_jogo_grupo")
@SequenceGenerator(name="jogoGrupo", sequenceName="sq_jogo_grupo", allocationSize=1)
public class JogoGrupoRel {

    @Id
    @Column(name="id_jogo_grupo")
    @GeneratedValue(generator="jogoGrupo", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_valido")
    private EN_Booleano valido;

    @Column(name="dt_encerramento")
    private LocalDateTime dtEncerramento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @ManyToOne
    @JoinColumn(name="id_jogo")
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name="id_grupo")
    private Grupo grupo;

    public void addJogo(Jogo jogo){
        jogo.addToList(this);
    }

    public void addGrupo(Grupo grupo){
        grupo.addToList(this);
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
    
}
