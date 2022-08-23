package br.com.almanak.almanakApi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_usuario")
@SequenceGenerator(name="usuario", sequenceName="sq_usuario", allocationSize=1)
public class Usuario {
   
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(generator="usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @Size(max=50)
    @Column(name="nm_usuario")
    private String name;

    @NotBlank
    @Size(min=12, max=50)
    @Column(name="ds_email")
    private String email;

    @NotNull
    @Size(max=20)
    @Column(name="ds_senha")
    private String senha;

    @NotBlank
    @Column(name="dt_nascimento")
    private LocalDate dtNascimento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @Transient
    private boolean maioridade;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario")
    private List<PlanoUsuarioRel> listPlanoUsuario = new ArrayList<PlanoUsuarioRel>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario")
    private List<Atividade> atividades = new ArrayList<Atividade>();


    public Usuario ajustar(){
        this.setMaioridade();

        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setMaioridade(this.maioridade);
        
        return usuario;
    
    }

    public void addToList(PlanoUsuarioRel planoUsuario){
        planoUsuario.setUsuario(this);
        this.getListPlanoUsuario().add(planoUsuario);
    }

    public void addToList(Atividade atividade){
        atividade.setUsuario(this);
        this.getAtividades().add(atividade);
    }

    public Usuario(Integer id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotBlank LocalDate dtNascimento, LocalDateTime dtRegistro,
            boolean maioridade, List<PlanoUsuarioRel> listPlanoUsuario, List<Atividade> atividades) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.maioridade = maioridade;
        this.listPlanoUsuario = listPlanoUsuario;
        this.atividades = atividades;
    }

    public Usuario(Integer id, @NotNull @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotNull LocalDate dtNascimento, LocalDateTime dtRegistro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.setMaioridade();
    }

    public Usuario(@NotNull @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotNull LocalDate dtNascimento, LocalDateTime dtRegistro) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.setMaioridade();
    }

    public Usuario(@NotNull @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotNull LocalDate dtNascimento) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.setMaioridade();
    }

    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public LocalDateTime getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(LocalDateTime localDateTime) {
        this.dtRegistro = localDateTime;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

    public boolean isMaioridade() {
        return maioridade;
    }
    public void setMaioridade(boolean maioridade) {
        this.maioridade = maioridade;
    }
    public void setMaioridade() {
        this.maioridade = ChronoUnit.YEARS.between(dtNascimento, LocalDate.now()) > 18;
    }

    public List<PlanoUsuarioRel> getListPlanoUsuario() {
        return listPlanoUsuario;
    }

    public void setListPlanoUsuario(List<PlanoUsuarioRel> listPlanoUsuario) {
        this.listPlanoUsuario = listPlanoUsuario;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    @Override
    public String toString() {
        return "Usuario [dtNascimento=" + dtNascimento + ", dtRegistro=" + dtRegistro + ", email=" + email
                + ", maioridade=" + maioridade + ", id=" + id + ", name=" + name + ", senha=" + senha + "]";
    }



}
