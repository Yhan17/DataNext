package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity

public class RetirarGrao extends DefaultEntity {
	private Double quantidade;
	private Motorista motorista;
	private Grao grao;
	private Armazem armazem;
	private Agricultor agricultor;
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
