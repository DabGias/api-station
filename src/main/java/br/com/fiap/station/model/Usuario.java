package br.com.fiap.station.model;

import org.springframework.hateoas.EntityModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "tb_usuario",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_email_usuario",
            columnNames = "email_usuario"
        ),
        @UniqueConstraint(
            name = "uk_cpf_usuario",
            columnNames = "cpf_usuario"
        )
    }
)
public class Usuario extends EntityModel<Usuario> {
    @Id
    @GeneratedValue(
        generator = "seq_usuario",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "seq_usuario",
        sequenceName = "seq_usuario",
        allocationSize = 1
    )
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "email_usuario")
    private String email;

    @Column(name = "nome_usuario")
    private String nome;

    @Column(name = "cpf_usuario")
    private String cpf;

    @Column(name = "senha_usuario")
    private String senha;

    public Usuario() {}

    public Usuario(Long id, String email, String nome, String cpf, String senha) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Usuario(String email, String nome, String cpf, String senha) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    // TODO: Criar método toModel()

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + "]";
    }
}
