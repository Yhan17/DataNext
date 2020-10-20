package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class Motorista extends DefaultEntity {
	private Pessoa pessoa;
	private Caminhao caminhao;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Caminhao getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(Caminhao caminhao) {
		this.caminhao = caminhao;
	}

}
