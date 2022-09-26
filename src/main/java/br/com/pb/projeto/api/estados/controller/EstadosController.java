package br.com.pb.projeto.api.estados.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pb.projeto.api.estados.controller.dto.EstadoDto;
import br.com.pb.projeto.api.estados.controller.form.EstadoForm;
import br.com.pb.projeto.api.estados.model.Estado;
import br.com.pb.projeto.api.estados.model.RegiaoEstado;
import br.com.pb.projeto.api.estados.repository.EstadoRepository;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadosController {

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public ResponseEntity<Page<EstadoDto>> lista(@RequestParam (required = false) String filtro, 
			@RequestParam (required = false) String regiao,
			@RequestParam (defaultValue = "0") int pagina, @RequestParam (defaultValue = "10") int qtd ) {
		
		Pageable paginacao;
		Page<Estado> estados;
		
		if(filtro == null) {
			paginacao = PageRequest.of(pagina, qtd);
		} else if (filtro.equalsIgnoreCase("populacao") || filtro.equalsIgnoreCase("area")) {
			paginacao = PageRequest.of(pagina, qtd, Direction.DESC, filtro);
		} else if (filtro.equalsIgnoreCase("nome") || filtro.equalsIgnoreCase("capital")) {
			paginacao = PageRequest.of(pagina, qtd, Direction.ASC, filtro);
		} else {
			return ResponseEntity.notFound().build();
		} 
		
		if(regiao != null) {
			try {
				RegiaoEstado regiaoEstado = RegiaoEstado.valueOf(regiao);
				estados = estadoRepository.findByRegiao(regiaoEstado, paginacao);
			} catch (IllegalArgumentException ex) {
				return ResponseEntity.notFound().build();
			}
		} else {
			estados = estadoRepository.findAll(paginacao);
		}
		
		return ResponseEntity.ok(EstadoDto.converteDto(estados));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstadoDto> id(@PathVariable Integer id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(new EstadoDto(estado.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder) { 
		Estado estado = form.converteForm(form); 
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/api/v1/estados/{id}").buildAndExpand(estado.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}
		
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadoDto> atualizar(@PathVariable Integer id, @RequestBody @Valid EstadoForm form) { 
		
		Optional<Estado> optional = estadoRepository.findById(id);
		
		if(optional.isPresent()) {
			Estado estado = form.atualizaForm(id, estadoRepository);
			return ResponseEntity.ok(new EstadoDto(estado));
			
		}
		return ResponseEntity.notFound().build();
	}
		
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id){
		
		Optional<Estado> optional = estadoRepository.findById(id);
		
		if(optional.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}