package br.unitins.datanext.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Motorista extends DefaultEntity<Motorista> {
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Pessoa pessoa;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
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
