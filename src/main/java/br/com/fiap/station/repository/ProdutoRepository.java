package br.com.fiap.station.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.station.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findByNomeContaining(Pageable pageable, String query);
}
