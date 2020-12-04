package br.unitins.datanext.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity

public class RetirarGrao extends DefaultEntity<RetirarGrao> {
	private Double quantidade;
	@ManyToOne
	private Motorista motorista;
	@ManyToOne
	private Grao grao;
	@ManyToOne
	private Armazem armazem;
	@ManyToOne
	private Agricultor agricultor;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Endereco endereco;

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Grao getGrao() {
		return grao;
	}

	public void setGrao(Grao grao) {
		this.grao = grao;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

	public Agricultor getAgricultor() {
		return agricultor;
	}

	public void setAgricultor(Agricultor agricultor) {
		this.agricultor = agricultor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
