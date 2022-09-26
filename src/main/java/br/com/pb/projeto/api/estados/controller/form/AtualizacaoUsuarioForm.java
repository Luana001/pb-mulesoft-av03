package br.com.pb.projeto.api.estados.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.pb.projeto.api.estados.model.Usuario;
import br.com.pb.projeto.api.estados.repository.UsuarioRepository;

public class AtualizacaoUsuarioForm {

	@NotNull @NotEmpty @Length(min = 5)
	String nome;
	@NotNull @NotEmpty @Length(min = 5)
	String senha;
	
	public Usuario atualizaForm(String id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		usuario.atualizar(AtualizacaoUsuarioForm.this);
		return usuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
