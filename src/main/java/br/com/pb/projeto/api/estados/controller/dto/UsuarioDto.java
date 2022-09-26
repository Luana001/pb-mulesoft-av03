package br.com.pb.projeto.api.estados.controller.dto;

import org.springframework.data.domain.Page;

import br.com.pb.projeto.api.estados.model.Usuario;

public class UsuarioDto {

	private String email;
	private String nome;
	
	public UsuarioDto() {

	}
	
	public UsuarioDto(Usuario usuario) {
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
	}

	public static Page<UsuarioDto> converteDto (Page<Usuario> usuarios){
		return usuarios.map(UsuarioDto::new);
	}
	
	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}
	
}
