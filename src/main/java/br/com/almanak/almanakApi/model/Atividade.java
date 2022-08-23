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

@Entity
@Table(name="tb_atividade")
@SequenceGenerator(name="atividade", sequenceName="sq_atividade", allocationSize=1)
public class Atividade {

    @Id
    @Column(name="id_atividade")
    @GeneratedValue(generator="atividade", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nm_tabela_alterada")
    private String tabelaAlterada; 
    
    @Column(name="ds_atividade")
    private String desc;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_tipo_atividade")
    private TipoAtividade tipoAtividade;

    public void addTipoAtividade(TipoAtividade tipoAtividade){
        tipoAtividade.addToList(this);
    }

    public void addUsuario(Usuario usuario){
        usuario.addToList(this);
    }
    
    public Atividade(Integer id, String tabelaAlterada, String desc, LocalDateTime dtRegistro, Usuario usuario,
            TipoAtividade tipoAtividade) {
        this.id = id;
        this.tabelaAlterada = tabelaAlterada;
        this.desc = desc;
        this.dtRegistro = dtRegistro;
        this.usuario = usuario;
        this.tipoAtividade = tipoAtividade;
    }

    public Atividade(String tabelaAlterada, String desc) {
        this.tabelaAlterada = tabelaAlterada;
        this.desc = desc;
    }


    public Atividade() {
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getTabelaAlterada() {
        return tabelaAlterada;
    }


    public void setTabelaAlterada(String tabelaAlterada) {
        this.tabelaAlterada = tabelaAlterada;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public LocalDateTime getDtRegistro() {
        return dtRegistro;
    }


    public void setDtRegistro(LocalDateTime dtRegistro) {
        this.dtRegistro = dtRegistro;
    }


    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }
    

    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }


    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }


    @Override
    public String toString() {
        return "Atividade [desc=" + desc + ", dtRegistro=" + dtRegistro + ", id=" + id + ", tabelaAlterada="
                + tabelaAlterada + ", tipoAtividade=" + tipoAtividade + ", usuario=" + usuario + "]";
    }
    
}
