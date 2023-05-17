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
    name = "tb_categoria",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_nome_categoria",
        columnNames = "nome_categoria"
    )
)
public class Categoria {
    @Id
    @GeneratedValue(
        generator = "seq_categoria",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "seq_categoria",
        sequenceName = "seq_categoria",
        allocationSize = 1
    )
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "nome_categoria")
    private String nome;

    @Column(name = "desc_categoria")
    private String descricao;

    public Categoria() {}

    public Categoria(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public EntityModel<Categoria> toModel() {
        return EntityModel.of(
            this
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
    }
}
