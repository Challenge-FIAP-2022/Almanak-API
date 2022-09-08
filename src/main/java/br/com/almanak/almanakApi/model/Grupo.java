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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_grupo")
@SequenceGenerator(name="grupo", sequenceName="sq_grupo", allocationSize=1)
public class Grupo {

    @Id
    @Column(name="id_grupo")
    @GeneratedValue(generator="grupo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="nm_grupo")
    private String name;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="grupo", cascade = CascadeType.ALL)
    private List<JogoGrupoRel> jogos  = new ArrayList<JogoGrupoRel>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="grupo", cascade = CascadeType.ALL)
    private List<UsuarioGrupoRel> usuarios  = new ArrayList<UsuarioGrupoRel>();

    public void addToList(JogoGrupoRel rel){
        rel.setGrupo(this);
        this.jogos.add(rel);
    }

    public void addToList(UsuarioGrupoRel rel){
        rel.setGrupo(this);
        this.usuarios.add(rel);
    }

    public Grupo(String name) {
        this.name = name;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

}
