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

@Entity
@Table(name="tb_usuario")
@SequenceGenerator(name="usuario", sequenceName="sq_usuario", allocationSize=1)
public class Usuario {
   
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(generator="usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
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

    @NotNull
    @Column(name="dt_nascimento")
    private LocalDate dtNascimento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario")
    @Transient
    private List<PlanoUsuarioRel> listPlanoUsuario = new ArrayList<PlanoUsuarioRel>();

    @Transient
    private boolean maioridade;

    public Usuario ajustar(){
        Usuario retorno = this;
        retorno.setMaioridade();
        retorno.setName(null);
        retorno.setEmail(null);
        retorno.setSenha(null);
        retorno.setDtNascimento(null);
        retorno.setDtRegistro(null);
        return retorno;
    }

    public Usuario(Integer id, @NotNull @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotNull LocalDate dtNascimento, LocalDateTime dtRegistro,
            List<PlanoUsuarioRel> listPlanoUsuario, boolean maioridade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.listPlanoUsuario = listPlanoUsuario;
        this.maioridade = maioridade;
    }

    public Usuario(@NotNull @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotNull LocalDate dtNascimento, LocalDateTime dtRegistro,
            List<PlanoUsuarioRel> listPlanoUsuario, boolean maioridade) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.listPlanoUsuario = listPlanoUsuario;
        this.maioridade = maioridade;
    }

    public Usuario(@NotNull @Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @NotNull @Size(max = 20) String senha, @NotNull LocalDate dtNascimento,
            List<PlanoUsuarioRel> listPlanoUsuario, boolean maioridade) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.listPlanoUsuario = listPlanoUsuario;
        this.maioridade = maioridade;
    }

    public Usuario(Integer id, String name, String email, String senha, LocalDate dtNascimento) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
    }

    public Usuario(String name, String email, String senha, LocalDate dtNascimento, LocalDateTime dtRegistro) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
    }

    public Usuario(String name, String email, String senha, LocalDate dtNascimento) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
    }
    
    public Usuario(Integer id, boolean maioridade) {
        this.id = id;
        this.maioridade = maioridade;
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

    public void setDtRegistro(LocalDateTime dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public boolean isMaioridade() {
        return maioridade;
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

    @Override
    public String toString() {
        return "Usuario [dtNascimento=" + dtNascimento + ", dtRegistro=" + dtRegistro + ", email=" + email
                + ", maioridade=" + maioridade + ", id=" + id + ", name=" + name + ", senha=" + senha + "]";
    }



}
