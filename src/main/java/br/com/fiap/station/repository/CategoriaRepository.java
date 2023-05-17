package br.com.fiap.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.station.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
