package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class MarcaVentilacao extends DefaultEntity<MarcaVentilacao> {
	private String marca;
	private String descricao;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
