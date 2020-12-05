package br.unitins.datanext.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Session;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.Cidade;
import br.unitins.datanext.models.Usuario;
import br.unitins.datanext.repository.CidadeRepository;
import br.unitins.datanext.repository.Repository;

@Named("perfilController")
@ViewScoped
public class AdminPerfilController extends Controller<Usuario>{

	private static final long serialVersionUID = -4673718992542714067L;
	
	
	public AdminPerfilController() {
		entity = (Usuario) Session.getInstance().getAttribute("usuarioLogado");	
	}


	@Override
	public Usuario getEntity() {
		if(entity == null)
			entity = new Usuario();
		return entity;
	}
	
	@Override
	public String salvar() {
		Repository<Usuario> repo = new Repository<Usuario>();
		if (getEntity().getSenha() != null)
			getEntity().setSenha(Util.hashSHA256(getEntity().getSenha()));
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserção Realizada com Sucesso!"));
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    Session.getInstance().setAttribute("usuarioLogado", getEntity());

			return "usuario.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println(e.getMessage());
			String duplicate = "duplicate key";
			if (e.getMessage().toLowerCase().contains(duplicate.toLowerCase())) {
				Util.addErrorMessage("Email já existe no sistema");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			} else {
				Util.addErrorMessage("Erro ao Salvar.");

			}
		}

		limpar();
		return "profile.xhtml?faces-redirect=true";
	}

	
	
	public List<Cidade> completeCidade(String query) {
		CidadeRepository repo = new CidadeRepository();
		try {
			return repo.findByNome(query, 6);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Cidade>();
		}
	}
	
}
