package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class Endereco extends DefaultEntity<Endereco> {
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
