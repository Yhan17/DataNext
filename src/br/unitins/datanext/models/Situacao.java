package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class Situacao extends DefaultEntity {
	private Double quantidadeArmazenada;
	private Armazem armazem;
	private StatusArmazem status;

	public Double getQuantidadeArmazenada() {
		return quantidadeArmazenada;
	}

	public void setQuantidadeArmazenada(Double quantidadeArmazenada) {
		this.quantidadeArmazenada = quantidadeArmazenada;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

	public StatusArmazem getStatus() {
		return status;
	}

	public void setStatus(StatusArmazem status) {
		this.status = status;
	}

}
