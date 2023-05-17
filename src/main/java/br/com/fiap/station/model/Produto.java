package br.com.fiap.station.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(
        generator = "seq_prodt",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "seq_prodt",
        sequenceName = "seq_prodt",
        allocationSize = 1
    )
    @Column(name = "id_prodt")
    private Long id;

    @Column(name = "nome_prodt")
    private String nome;

    @Column(name = "preco_prodt")
    private BigDecimal preco;

    @Column(name = "desc_prodt")
    private String descricao;

    @Column(name = "qtde_prodt")
    private BigInteger quantidade;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinTable(
        name = "tb_categorias_produto",
        joinColumns = @JoinColumn(
            name = "id_prodt",
            referencedColumnName = "id_prodt",
            foreignKey = @ForeignKey(name = "fk_tb_produto")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "id_categoria",
            referencedColumnName = "id_categoria",
            foreignKey = @ForeignKey(name = "fk_tb_categoria_produto")
        )
    )
    private Set<Categoria> categorias = new LinkedHashSet<>();

    public Produto() {}

    public Produto(Long id, String nome, BigDecimal preco, String descricao, BigInteger quantidade, Set<Categoria> categorias) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categorias = categorias;
    }

    public Produto(String nome, BigDecimal preco, String descricao, BigInteger quantidade, Set<Categoria> categorias) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categorias = categorias;
    }

    public void addCategoria(Categoria c) {
        this.categorias.add(c);
    }

    public void rmvCategoria(Categoria c) {
        this.categorias.remove(c);
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigInteger quantidade) {
        this.quantidade = quantidade;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao
                + ", quantidade=" + quantidade + ", categorias=" + categorias + "]";
    }
}
