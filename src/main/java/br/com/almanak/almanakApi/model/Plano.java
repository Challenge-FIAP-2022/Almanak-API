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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_plano")
@SequenceGenerator(name="plano", sequenceName="sq_plano", allocationSize=1)
public class Plano {

    @Id
    @Column(name="id_plano")
    @GeneratedValue(generator="plano", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @Column(name="nm_plano")
    private String name; 
    
    @Column(name="ds_plano")
    private String desc;

    @Column(name="vl_plano")
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Type(type="PostgreSQLEnumType")
    @Column(name="fl_valido")
    private EN_Booleano valido;

    @Column(name="dt_encerramento")
    private LocalDateTime dtEncerramento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="plano")
    private List<Contrato> contratos = new ArrayList<Contrato>();

    public void addToList(Contrato contrato){
        contrato .setPlano(this);
        this.getContratos().add(contrato);
    }

    public Plano(Integer id, @NotNull String name, String desc, Double valor, EN_Booleano valido,
            LocalDateTime dtEncerramento, LocalDateTime dtRegistro) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
        this.dtEncerramento = dtEncerramento;
        this.dtRegistro = dtRegistro;
    }

    public Plano(@NotNull String name, String desc, Double valor, EN_Booleano valido, LocalDateTime dtRegistro) {
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        this.valido = valido;
        this.dtRegistro = dtRegistro;
    }

    public Plano(@NotNull String name, String desc, Double valor) {
        this.name = name;
        this.desc = desc;
        this.valor = valor;
        if(this.getValido() == null)
            this.valido = EN_Booleano.sim;
    }

    public Plano(@NotNull String name, Double valor) {
        this.name = name;
        this.valor = valor;
    }

    public Plano(@NotNull String name) {
        this.name = name;
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
