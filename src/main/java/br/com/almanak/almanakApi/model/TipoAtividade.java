package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_tipo_atividade")
@SequenceGenerator(name="tipoAtividade", sequenceName="sq_tipo_atividade", allocationSize=1)
public class TipoAtividade {

    @Id
    @Column(name="id_tipo_atividade")
    @GeneratedValue(generator="tipoAtividade", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @Column(name="nm_grupo")
    private String grupo; 
    
    @Column(name="nm_subgrupo")
    private String subGrupo;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="tipoAtividade", cascade = CascadeType.ALL)
    List<Atividade> atividades = new ArrayList<Atividade>();

    public void addToList(Atividade atividade){
        atividade.setTipoAtividade(this);
        this.atividades.add(atividade);
    }

    public TipoAtividade(Integer id, @NotBlank String grupo, String subGrupo, LocalDateTime dtRegistro) {
        this.id = id;
        this.grupo = grupo;
        this.subGrupo = subGrupo;
        this.dtRegistro = dtRegistro;
    }

    public TipoAtividade(@NotBlank String grupo, String subGrupo, List<Atividade> atividades) {
        this.grupo = grupo;
        this.subGrupo = subGrupo;
        this.atividades = atividades;
    }

    public TipoAtividade(@NotBlank String grupo, String subGrupo) {
        this.grupo = grupo;
        this.subGrupo = subGrupo;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }
    
}
