package br.com.almanak.almanakApi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_usuario")
@SequenceGenerator(name="usuario", sequenceName="sq_usuario", allocationSize=1)
public class Usuario implements UserDetails{
   
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(generator="usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(max=50)
    @Column(name="nm_usuario")
    private String name;

    @NotBlank
    @Column(name="ds_email")
    private String email;

    @Column(name="ds_senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(name="dt_nascimento")
    private LocalDate dtNascimento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @Transient
    private boolean maioridade;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<Contrato> contratos = new ArrayList<Contrato>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<Atividade> atividades = new ArrayList<Atividade>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<Jogo> jogos = new ArrayList<Jogo>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<UsuarioGrupoRel> grupos  = new ArrayList<UsuarioGrupoRel>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="usuario", cascade = CascadeType.ALL)
    private List<UsuarioRoleRel> roles = new ArrayList<>();

    public void addToList(Contrato contrato){
        contrato.setUsuario(this);
        this.getContratos().add(contrato);
    }

    public void addToList(Atividade atividade){
        atividade.setUsuario(this);
        this.getAtividades().add(atividade);
    }

    public void addToList(Jogo jogo){
        jogo.setUsuario(this);
        this.getJogos().add(jogo);
    }

    public void addToList(Avaliacao avaliacao){
        avaliacao.setUsuario(this);
        this.getAvaliacoes().add(avaliacao);
    }

    public void addToList(UsuarioGrupoRel rel){
        rel.setUsuario(this);
        this.grupos.add(rel);
    }

    public void addToList(UsuarioRoleRel rel){
        rel.setUsuario(this);
        this.roles.add(rel);
    }

    public Usuario(Integer id, @Size(max = 50) String name, @Size(min = 12, max = 50) String email,
            @Size(max = 20) String senha, LocalDate dtNascimento, LocalDateTime dtRegistro, boolean maioridade,
            List<Contrato> contratos, List<Atividade> atividades, List<Jogo> jogos) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.maioridade = maioridade;
        this.contratos = contratos;
        this.atividades = atividades;
        this.jogos = jogos;
    }

    public Usuario(Integer id, @Size(max = 50) String name, @Size(min = 12, max = 50) String email,
            @Size(max = 20) String senha, LocalDate dtNascimento, LocalDateTime dtRegistro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
    }

    public Usuario(@Size(max = 50) String name, @Size(min = 12, max = 50) String email, @Size(max = 20) String senha,
            LocalDate dtNascimento) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
    }

    public Usuario(@Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @Size(max = 20) String senha, LocalDate dtNascimento, LocalDateTime dtRegistro) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
    }

    public Usuario(@Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @Size(max = 20) String senha, List<UsuarioRoleRel> roles) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario(@Size(max = 50) String name, @NotBlank @Size(min = 12, max = 50) String email,
            @Size(max = 20) String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(@NotBlank @Size(min = 12, max = 50) String email, @Size(max = 20) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }

    public void setMaioridade() {
        this.maioridade = ChronoUnit.YEARS.between(dtNascimento, LocalDate.now()) > 18;
    }

    public Plano getPlanoValido() {
        Plano plano = new Plano();

        this.getContratos();
        for(Contrato c : contratos){
            if(c.getValido() == EN_Booleano.sim){
                plano = c.getPlano();
            }
        }
        
        return plano;

    }

    public Grupo getGrupoValido() {
        Grupo grupo = new Grupo();

        this.getGrupos();
        for(UsuarioGrupoRel g : grupos){
            if(g.getValido() == EN_Booleano.sim){
                grupo = g.getGrupo();
            }
        }
        
        return grupo;

    }

    public Usuario withRole(UsuarioRoleRel role){
        Assert.notNull(role, "role is required");
        this.roles.add(role);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Role> roles = new ArrayList<>();

        for(UsuarioRoleRel r : this.roles){
            roles.add(r.getRole());
        }

        return roles;

    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
