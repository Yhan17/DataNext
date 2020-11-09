package br.unitins.datanext.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.Categoria;
import br.unitins.datanext.repository.CategoriaRepository;
import br.unitins.datanext.repository.Repository;

@Named("adminCategoriaController")
@ViewScoped
public class AdminCategoriaController extends Controller<Categoria>{

	private static final long serialVersionUID = -1647884370920156458L;
	private String filtro;
	private List<Categoria> listaCategoria;

	public AdminCategoriaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashCategoria");
		entity = (Categoria) flash.get("flashCategoria");	
	}
	
	@Override
	public Categoria getEntity() {
		if (entity == null) {
			entity = new Categoria();
		}
		return entity;
	}
	
	@Override
	public String salvar() {
		Repository<Categoria> repo = new Repository<Categoria>();
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage("Inserção Realizada com Sucesso!"));
					
					
					FacesContext.getCurrentInstance()
						    .getExternalContext()
						    .getFlash().setKeepMessages(true);
//			Util.addInfoMessage("Operação realizada com sucesso.");
			return "categoria.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "categoriaForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(Categoria entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashCategoria", entity);
		return "categoriaForm.xhtml?faces-redirect=true";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public void excluir(Categoria obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaRepository repo = new CategoriaRepository();
		try {
			setListaCategoria(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaCategoria(null);
		}
		
	}
	
	public List<Categoria> getListaCategoria() {
		if(listaCategoria == null) {
			listaCategoria = new ArrayList<Categoria>();
			EntityManager em = JPAUtil.getEntityManager();
			CategoriaRepository repo = new CategoriaRepository();
			try {
				setListaCategoria(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaCategoria(null);
			}
		}
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaRepository repo = new CategoriaRepository();
		try {
			setListaCategoria(repo.findByUn(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaCategoria(null);
		}
	}
}
