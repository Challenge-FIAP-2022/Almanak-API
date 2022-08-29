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
@Table(name="tb_contrato")
@SequenceGenerator(name="contrato", sequenceName="sq_contrato", allocationSize=1)
public class Contrato {

    @Id
    @Column(name="id_contrato")
    @GeneratedValue(generator="contrato", strategy = GenerationType.SEQUENCE)
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
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_plano")
    private Plano plano;

    public void addDependencies(Usuario usuario, Plano plano){
        usuario.addToList(this);
        plano.addToList(this);
    }

    public void addPlano(Plano plano){
        plano.addToList(this);
    }

    public void addUsuario(Usuario usuario){
        usuario.addToList(this);
    }

    public Contrato(Integer id, Usuario usuario, Plano plano, EN_Booleano valido, LocalDateTime dtEncerramento,
        LocalDateTime dtRegistro) {
        this.id = id;
        this.usuario = usuario;
        this.plano = plano;
        this.valido = valido;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
    }
    
    public Contrato(Usuario usuario, Plano plano, EN_Booleano valido, LocalDateTime dtRegistro) {
        this.usuario = usuario;
        this.plano = plano;
        this.valido = valido;
        this.dtRegistro = dtRegistro;
    }

    public Contrato(Usuario usuario, Plano plano, EN_Booleano valido) {
        this.addDependencies(usuario, plano);
        this.valido = valido;
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
