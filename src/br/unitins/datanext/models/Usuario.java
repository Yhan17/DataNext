package br.unitins.datanext.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "\"usuario\"")
public class Usuario extends DefaultEntity<Usuario>  {


	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
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
