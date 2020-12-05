package br.unitins.datanext.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.Situacao;
import br.unitins.datanext.repository.SituacaoRepository;

@Named("adminSituacaoArmazemController")
@ViewScoped
public class AdminSituacaoArmazemController implements Serializable {

	private static final long serialVersionUID = -7207543831245782644L;
	private List<Situacao> listaSituacao;

	public List<Situacao> getListaSituacao() {
		if(listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
			EntityManager em = JPAUtil.getEntityManager();
			SituacaoRepository repo = new SituacaoRepository();
			try {
				setListaSituacao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaSituacao(null);
			}
		}
		return listaSituacao;
	}

	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}
	
	public void generateRelatorio() {
		Util.redirect("/DataNext/armazemreport");

	}

}
