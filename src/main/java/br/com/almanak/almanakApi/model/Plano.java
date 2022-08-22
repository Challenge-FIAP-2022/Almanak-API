package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;

@Entity
@Table(name="tb_plano")
@SequenceGenerator(name="plano", sequenceName="sq_plano", allocationSize=1)
public class Plano {

    @Id
    @Column(name="id_plano")
    @GeneratedValue(generator="plano", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Column(name="nm_plano")
    private String name; 
    
    @Column(name="ds_plano")
    private String desc;

    @Column(name="vl_plano")
    private Float valor;

    @Enumerated
    @Column(name="fl_valido")
    private EN_Booleano valido;

    @Column(name="dt_encerramento")
    private LocalDateTime dtEncerramento;

    @Column(name="dtRegistro")
    private LocalDateTime dtRegistro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="plano")
    private List<PlanoUsuarioRel> listPlanoUsuario = new ArrayList<PlanoUsuarioRel>();

    public void addPlano(PlanoUsuarioRel planoUsuario){
        planoUsuario.setPlano(this);
        this.getListPlanoUsuario().add(planoUsuario);
    }

    public Plano(Integer id, @NotNull String name, String desc, Float valor, EN_Booleano valido,
            LocalDateTime dtEncerramento, LocalDateTime dtRegistro, List<PlanoUsuarioRel> planoUsuario) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
        this.listPlanoUsuario = planoUsuario;
    }

    public Plano(Integer id, @NotNull String name, String desc, Float valor, EN_Booleano valido,
            LocalDateTime dtEncerramento, LocalDateTime dtRegistro) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
    }

    public Plano(@NotNull String name, String desc, Float valor, EN_Booleano valido,
            List<PlanoUsuarioRel> planoUsuario) {
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
        this.listPlanoUsuario = planoUsuario;
    }

    public Plano(@NotNull String name, String desc, Float valor, EN_Booleano valido, LocalDateTime dtRegistro) {
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
        this.dtRegistro = dtRegistro;
    }

    public Plano(@NotNull String name, String desc, Float valor, EN_Booleano valido) {
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
    }

    public Plano(@NotNull String name, Float valor, EN_Booleano valido) {
        this.name = name;
        this.valor = valor;
        this.valido = valido;
    }

    public Plano() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
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

    public List<PlanoUsuarioRel> getListPlanoUsuario() {
        return listPlanoUsuario;
    }

    public void setListPlanoUsuario(List<PlanoUsuarioRel> lista) {
        this.listPlanoUsuario = lista;
    }
    
    @Override
    public String toString() {
        return "Plano [desc=" + desc + ", dtEncerramento=" + dtEncerramento + ", dtRegistro=" + dtRegistro + ", id="
                + id + ", name=" + name + ", valido=" + valido + ", valor=" + valor + "]";
    }

}
