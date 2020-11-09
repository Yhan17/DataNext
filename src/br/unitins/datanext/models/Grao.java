package br.unitins.datanext.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Grao extends DefaultEntity<Grao> {
	private String nome;
	private String descricao;
	@ManyToOne
	private UnidadeDeMedida unidadeDeMedida;
	@ManyToOne
	private Categoria categoria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
