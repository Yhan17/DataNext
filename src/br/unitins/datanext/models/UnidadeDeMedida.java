package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class UnidadeDeMedida extends DefaultEntity<UnidadeDeMedida> {
	private String unidadeDeMedida;
	private String descricao;

	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
