package br.com.fiap.station.model;

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
    uniqueConstraints = @UniqueConstraint(
        name = "uk_email_usuario",
        columnNames = "email_usuario"
    )
)
public class Usuario {
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

    @Column(name = "senha_usuario")
    private String senha;


    @Column(name = "tel_usuario")
    private String tel;

    public Usuario() {}

    public Usuario(Long id, String email, String nome, String senha, String tel) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.tel = tel;
    }

    public Usuario(String email, String nome, String senha, String tel) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.tel = tel;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", nome=" + nome + ", senha=" + senha + ", tel=" + tel + "]";
    }
}
