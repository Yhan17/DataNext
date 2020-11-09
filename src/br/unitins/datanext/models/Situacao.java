package br.unitins.datanext.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Situacao extends DefaultEntity<Situacao> {
	private Double quantidadeArmazenada;
	@ManyToOne
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
