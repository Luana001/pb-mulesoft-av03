package br.com.pb.projeto.api.estados.controller.dto;

import org.springframework.data.domain.Page;

import br.com.pb.projeto.api.estados.model.Estado;
import br.com.pb.projeto.api.estados.model.RegiaoEstado;

public class EstadoDto {

	private Integer id;
	private String nome;
	private RegiaoEstado regiao;
	private long populacao;
	private String capital;
	private float area;

	public EstadoDto(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.regiao = estado.getRegiao();
		this.populacao = estado.getPopulacao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
	}
	
	public static Page<EstadoDto> converteDto(Page<Estado> estados) {
		return estados.map(EstadoDto::new);
	}
	
	public String getNome() {
		return nome;
	}

	public RegiaoEstado getRegiao() {
		return regiao;
	}

	public long getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}
	
	public float getArea() {
		return area;
	}

	public Integer getId() {
		return id;
	}
}
