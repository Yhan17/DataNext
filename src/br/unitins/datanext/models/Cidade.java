package br.unitins.datanext.models;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;

@Entity
public class Cidade extends DefaultEntity<Cidade>{

	private String nome;
	@ManyToOne
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
