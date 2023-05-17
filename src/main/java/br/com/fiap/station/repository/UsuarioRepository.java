package br.com.fiap.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.station.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
