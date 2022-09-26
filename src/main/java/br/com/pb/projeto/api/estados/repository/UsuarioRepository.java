package br.com.pb.projeto.api.estados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pb.projeto.api.estados.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
}
