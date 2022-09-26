package br.com.pb.projeto.api.estados.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pb.projeto.api.estados.config.validacao.EmailExistenteException;
import br.com.pb.projeto.api.estados.controller.dto.UsuarioDto;
import br.com.pb.projeto.api.estados.controller.form.AtualizacaoUsuarioForm;
import br.com.pb.projeto.api.estados.controller.form.UsuarioForm;
import br.com.pb.projeto.api.estados.model.Usuario;
import br.com.pb.projeto.api.estados.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public Page<UsuarioDto> listaUsuario(@PageableDefault(page = 0, size = 10) Pageable paginacao ) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return UsuarioDto.converteDto(usuarios);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) { 
		
		if(usuarioRepository.findById(form.getEmail()).isPresent()) {
			throw new EmailExistenteException();
		}
		
		Usuario usuario = form.converteForm(form); 
		usuarioRepository.save(usuario);
			
		URI uri = uriBuilder.path("/api/v1/usuarios/{id}").buildAndExpand(usuario.getEmail()).toUri();
			
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable String id, @RequestBody @Valid AtualizacaoUsuarioForm form) { 
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			Usuario usuario = form.atualizaForm(id, usuarioRepository); 
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		return ResponseEntity.notFound().build();
		
	}
}
