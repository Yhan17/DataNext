package br.unitins.datanext.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"Usuario\"")
public class Usuario extends DefaultEntity {

	private Pessoa pessoa;
	private String senha;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
