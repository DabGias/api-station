package br.com.fiap.station.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.hateoas.EntityModel;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(
        generator = "seq_pedido",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "seq_pedido",
        sequenceName = "seq_pedido",
        allocationSize = 1
    )
    @Column(name = "id_pedido")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_pedido")
    private LocalDate dataPedido;

    @Column(name = "forma_entrega_pedido")
    private String formaEntrega;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinTable(
        name = "tb_produtos_pedido",
        joinColumns = @JoinColumn(
            name = "id_pedido",
            referencedColumnName = "id_pedido",
            foreignKey = @ForeignKey(name = "fk_tb_pedido")
        ),
        inverseJoinColumns = @JoinColumn(
            name = "id_prodt",
            referencedColumnName = "id_prodt",
            foreignKey = @ForeignKey(name = "fk_tb_produto_pedido")
        )
    )
    private Set<Produto> produtos = new LinkedHashSet<>();

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinColumn(
        name = "id_usuario",
        referencedColumnName = "id_usuario",
        foreignKey = @ForeignKey(name = "fk_tb_usuario")
    )
    private Usuario usuario;

    public Pedido() {}

    public Pedido(Long id, LocalDate dataPedido, String formaEntrega, Set<Produto> produtos, Usuario usuario) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.formaEntrega = formaEntrega;
        this.produtos = produtos;
        this.usuario = usuario;
    }

    public Pedido(LocalDate dataPedido, String formaEntrega, Set<Produto> produtos, Usuario usuario) {
        this.dataPedido = dataPedido;
        this.formaEntrega = formaEntrega;
        this.produtos = produtos;
        this.usuario = usuario;
    }

    public EntityModel<Pedido> toModel() {
        return EntityModel.of(this);
    }

    public void addProdt(Produto p) {
        this.produtos.add(p);
    }

    public void rmvProdt(Produto p) {
        this.produtos.remove(p);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getFormaEntrega() {
        return formaEntrega;
    }

    public void setFormaEntrega(String formaEntrega) {
        this.formaEntrega = formaEntrega;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", dataPedido=" + dataPedido + ", formaEntrega=" + formaEntrega + ", produtos="
                + produtos + ", usuario=" + usuario + "]";
    }
}
