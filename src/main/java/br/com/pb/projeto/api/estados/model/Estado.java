package br.com.pb.projeto.api.estados.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.pb.projeto.api.estados.controller.form.EstadoForm;

@Entity
public class Estado {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private RegiaoEstado regiao;
	private long populacao;
	private String capital;
	private float area;
	
	public Estado(){
	}
	
	public Estado(EstadoForm form) {
		this.nome = form.getNome();
		this.regiao = form.getRegiao();
		this.populacao = form.getPopulacao();
		this.capital = form.getCapital();
		this.area = form.getArea();
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

	public void setRegiao(RegiaoEstado regiao) {
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
	
	public Integer getId() { 
		return id; 
	}
}
