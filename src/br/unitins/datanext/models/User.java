package br.unitins.datanext.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="\"User\"")
public class User extends DefaultEntity {

	private String name;
	private String login;
	private String password;
	private Date created_at;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", login=" + login + ", password=" + password + ", created_at=" + created_at
				+ "]";
	}
	
	

}
