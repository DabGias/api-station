package br.com.fiap.station.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.station.model.Categoria;
import br.com.fiap.station.model.Pedido;
import br.com.fiap.station.model.Produto;
import br.com.fiap.station.model.Usuario;
import br.com.fiap.station.repository.CategoriaRepository;
import br.com.fiap.station.repository.PedidoRepository;
import br.com.fiap.station.repository.ProdutoRepository;
import br.com.fiap.station.repository.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    UsuarioRepository repoUsuario;

    @Autowired
    CategoriaRepository repoCategoria;

    @Autowired
    ProdutoRepository repoProduto;

    @Autowired
    PedidoRepository repoPedido;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario1 = new Usuario("usuario1@email.com.br", "Pedro Silva Santos", "111.111.111-10", encoder.encode("12345678"));
        Usuario usuario2 = new Usuario("usuario2@email.com.br", "João Da Silva Dos Santos", "111.111.111-11", encoder.encode("12345678"));
        Usuario usuario3 = new Usuario("usuario3@email.com.br", "Marcelo Miklos", "111.111.111-12", encoder.encode("12345678"));
        
        Categoria cat1 = new Categoria("Roupas", "Roupas de diversos estilos e modelos!");
        Categoria cat2 = new Categoria("Eletrônicos", "Os modelos mais avançados e atualizados!");
        Categoria cat3 = new Categoria("Móveis", "Decoração moderna e atual para sua casa!");

        Produto prodt1 = new Produto();

        prodt1.setNome("Camiseta do Jar Jar Binks");
        prodt1.setPreco(new BigDecimal("100.00"));
        prodt1.setDescricao("Camiseta do personagem mais amado da franquia Start Wars (Guerra nas Estrelas)!");
        prodt1.addCategoria(cat1);
        
        Produto prodt2 = new Produto();
        
        prodt2.setNome("Sofá-cama");
        prodt2.setPreco(new BigDecimal("7000.00"));
        prodt2.setDescricao("O melhor sofá e a melhor cama!");
        prodt2.addCategoria(cat2);

        Produto prodt3 = new Produto();
        
        prodt3.setNome("Celular");
        prodt3.setPreco(new BigDecimal("3000.00"));
        prodt3.setDescricao("O novo celular do momento!");
        prodt3.addCategoria(cat3);

        Pedido pedido1 = new Pedido();

        pedido1.setDataPedido(LocalDate.now());
        pedido1.setFormaEntrega("SEDEX");
        pedido1.setUsuario(usuario1);
        pedido1.addProdt(prodt1);
        pedido1.addProdt(prodt2);

        Pedido pedido2 = new Pedido();

        pedido2.setDataPedido(LocalDate.now());
        pedido2.setFormaEntrega("FedEx");
        pedido2.setUsuario(usuario2);
        pedido2.addProdt(prodt2);
        pedido2.addProdt(prodt3);

        Pedido pedido3 = new Pedido();

        pedido3.setDataPedido(LocalDate.now());
        pedido3.setFormaEntrega("Correios");
        pedido3.setUsuario(usuario3);
        pedido3.addProdt(prodt3);
        pedido3.addProdt(prodt1);
        
        repoPedido.saveAll(List.of(
            pedido1, pedido2, pedido3
        ));
        
        repoProduto.saveAll(List.of(
            prodt1, prodt2, prodt3
        ));

        repoCategoria.saveAll(List.of(
            cat1, cat2, cat3
        ));
        
        repoUsuario.saveAll(List.of(
            usuario1, usuario2, usuario3
        ));
    }
}
