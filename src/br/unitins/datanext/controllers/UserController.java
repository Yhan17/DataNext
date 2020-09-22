package br.unitins.datanext.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.User;

@Named
@ViewScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 6390586700046658201L;
	private User user;
	private List<User> listaUser;
	private String login;
	private String senha;

	public void salvar() {
		String senha = Util.hashSHA256(getUser().getPassword());

		getUser().setPassword(senha);

		EntityManager em = JPAUtil.getEntityManager();
		// iniciando uma transacao com o banco de dados
		em.getTransaction().begin();
		em.merge(getUser());
		em.getTransaction().commit();

		Util.addInfoMessage("Operação realizada com sucesso.");
		limpar();
	}

	private void limpar() {
		user = null;
	}

	public void excluir() {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		User produto = em.merge(getUser());
		em.remove(produto);
		em.getTransaction().commit();

		Util.addInfoMessage("Remoção realizada com sucesso.");
		limpar();
	}

	public void pesquisarUsuario() {
		EntityManager em = JPAUtil.getEntityManager();

		Query query = em.createQuery("SELECT u FROM User u ");
		setListaUser(query.getResultList());
	}

	public User getUser() {
		if (user == null)
			user = new User();
		return user;
	}

	public String logar() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<User> query = em.createQuery("from User u where u.login = :identificacao and u.password = :senha", User.class);
		String login =  getLogin();
		String senha = Util.hashSHA256(getSenha());
		query.setParameter("identificacao",login);
		query.setParameter("senha",senha);
		try {
		    setUser(query.getSingleResult());
		    Util.addInfoMessage("Usuário Encontrado");
			return "index.xhtml?faces-redirect=true";
		} catch( javax.persistence.NoResultException e ){
			Util.addErrorMessage("Usuário Não encontrado");
			return "";
		}
	}

	public void setUser(User usuer) {
		this.user = usuer;
	}

	public List<User> getListaUser() {
		return listaUser;
	}

	public void editar(User user) {
		setUser(user);
	}

	public void setListaUser(List<User> listaUser) {
		this.listaUser = listaUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
