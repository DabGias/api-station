package br.com.fiap.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.station.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
