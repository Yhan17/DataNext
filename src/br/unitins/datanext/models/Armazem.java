package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class Armazem extends DefaultEntity {
	private String descricao;
	private String sigla;
	private Boolean status;
	private Double capacidade;
	private String imagem;
	private Localizacao localizacao;
	private Ventilacao ventilacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Ventilacao getVentilacao() {
		return ventilacao;
	}

	public void setVentilacao(Ventilacao ventilacao) {
		this.ventilacao = ventilacao;
	}

}
