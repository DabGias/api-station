package br.com.fiap.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.station.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
