package br.com.pb.projeto.api.estados.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.pb.projeto.api.estados.model.Estado;
import br.com.pb.projeto.api.estados.model.RegiaoEstado;
import br.com.pb.projeto.api.estados.repository.EstadoRepository;

public class EstadoForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull 
	private RegiaoEstado regiao;
	@NotNull @Positive 
	private long populacao;
	@NotNull @NotEmpty @Length(min = 5)
	private String capital;
	@NotNull @Positive
	private float area;
	
	public Estado converteForm(EstadoForm form) {
		return new Estado(form);
	}
	
	public Estado atualizaForm(Integer id, EstadoRepository estadoRepository) {
		Estado estado = estadoRepository.getReferenceById(id);
		estado.setNome(this.nome);
		estado.setRegiao(this.regiao);
		estado.setPopulacao(this.populacao);
		estado.setCapital(this.capital);
		estado.setArea(this.area);
		return estado;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public RegiaoEstado getRegiao() {
		return regiao;
	}

	public void RegiaoEstado(RegiaoEstado regiao) {
		this.regiao = regiao;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

}
