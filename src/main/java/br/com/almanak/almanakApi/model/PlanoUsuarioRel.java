package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;

@Entity
@Table(name="tb_plano_usuario")
@SequenceGenerator(name="planoUsuario", sequenceName="sq_plano_usuario", allocationSize=1)
public class PlanoUsuarioRel {

    @Id
    @Column(name="id_plano_usuario")
    @GeneratedValue(generator="planoUsuario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Transient
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plano")
    private Plano plano;

    @Column(name="fl_valido")
    private EN_Booleano valido;

    @Column(name="dt_encerramento")
    private LocalDateTime dtEncerramento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    public PlanoUsuarioRel(Integer id, Usuario usuario, Plano plano, EN_Booleano valido, LocalDateTime dtEncerramento,
        LocalDateTime dtRegistro) {
        this.id = id;
        this.usuario = usuario;
        this.plano = plano;
        this.valido = valido;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
    }
    
    public PlanoUsuarioRel(Usuario usuario, Plano plano, EN_Booleano valido, LocalDateTime dtRegistro) {
        this.usuario = usuario;
        this.plano = plano;
        this.valido = valido;
        this.dtRegistro = dtRegistro;
    }

    public PlanoUsuarioRel(Usuario usuario, Plano plano, EN_Booleano valido) {
        this.usuario = usuario;
        this.plano = plano;
        this.valido = valido;
    }

    public PlanoUsuarioRel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public EN_Booleano getValido() {
        return valido;
    }

    public void setValido(EN_Booleano valido) {
        this.valido = valido;
    }

    public LocalDateTime getDtEncerramento() {
        return dtEncerramento;
    }

    public void setDtEncerramento(LocalDateTime dtEncerramento) {
        this.dtEncerramento = dtEncerramento;
    }

    public LocalDateTime getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(LocalDateTime dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    @Override
    public String toString() {
        return "PlanoUsuarioRel [dtEncerramento=" + dtEncerramento + ", dtRegistro=" + dtRegistro + ", id=" + id
                + ", plano=" + plano + ", usuario=" + usuario + ", valido=" + valido + "]";
    }

}
