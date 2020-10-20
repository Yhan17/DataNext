package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class Agricultor extends DefaultEntity {
	private Pessoa pessoa;
	private String imagem;
	private Boolean status;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
