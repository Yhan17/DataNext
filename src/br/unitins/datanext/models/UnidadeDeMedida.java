package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class UnidadeDeMedida extends DefaultEntity {
	private String unidadeMedida;
	private String descricao;

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
