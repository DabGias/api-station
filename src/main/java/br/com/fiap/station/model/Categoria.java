package br.com.fiap.station.model;

import org.springframework.hateoas.EntityModel;

import br.com.fiap.station.controller.CategoriaController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Pageable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

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

    @NotBlank
    @Column(name = "nome_categoria")
    private String nome;

    @NotBlank
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
            this,
            linkTo(methodOn(CategoriaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(CategoriaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(CategoriaController.class).index(Pageable.unpaged())).withRel("listAll")
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
