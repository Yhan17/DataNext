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
import br.unitins.datanext.models.Grao;
import br.unitins.datanext.models.UnidadeDeMedida;
import br.unitins.datanext.repository.CategoriaRepository;
import br.unitins.datanext.repository.GraoRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.UnidadeDeMedidaRepository;
@Named("adminGraoController")
@ViewScoped
public class AdminGraoController extends Controller<Grao> {

	private static final long serialVersionUID = -1538497428270674021L;
	
	private UnidadeDeMedida unidadeDeMedida;
	private Categoria categoria;
	private List<Grao> listaGrao;
	private List<UnidadeDeMedida> listaUnidadeMedida;
	private List<Categoria> listaCategoria;
	
	public AdminGraoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashGrao");
		entity = (Grao) flash.get("flashGrao");	
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

	@Override
	public Grao getEntity() {
		if (entity == null) {
			entity = new Grao();
			entity.setCategoria(new Categoria());
			entity.setUnidadeDeMedida(new UnidadeDeMedida());
		}
		return entity;
	}
	
	public List<UnidadeDeMedida> getListaUnidadeMedida() {
		if (listaUnidadeMedida == null) {
			UnidadeDeMedidaRepository repo = new UnidadeDeMedidaRepository();
			try {
				setListaUnidadeMedida(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaUnidadeMedida(new ArrayList<UnidadeDeMedida>());
			}
		}
		return listaUnidadeMedida;
	}

	public void setListaUnidadeMedida(List<UnidadeDeMedida> listaUnidadeMedida) {
		this.listaUnidadeMedida = listaUnidadeMedida;
	}

	public List<Categoria> getListaCategoria() {
		if (listaCategoria == null) {
			CategoriaRepository repo = new CategoriaRepository();
			try {
				setListaCategoria(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaCategoria(new ArrayList<Categoria>());
			}
		}
		return listaCategoria;	
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	@Override
	public String salvar() {
		Repository<Grao> repo = new Repository<Grao>();
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
			return "graos.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "graosForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(Grao entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashGrao", entity);
		return "graosForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public void excluir(Grao obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		GraoRepository repo = new GraoRepository();
		try {
			setListaGrao(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaGrao(null);
		}
		
	}
	
	public List<Grao> getListaGrao() {
		if(listaGrao == null) {
			listaGrao = new ArrayList<Grao>();
			EntityManager em = JPAUtil.getEntityManager();
			GraoRepository repo = new GraoRepository();
			try {
				setListaGrao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaGrao(null);
			}
		}
		return listaGrao;
	}

	public void setListaGrao(List<Grao> listaMarcaVentilacao) {
		this.listaGrao = listaMarcaVentilacao;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		GraoRepository repo = new GraoRepository();
		try {
			if(getUnidadeDeMedida() != null && getCategoria() != null)
				setListaGrao(repo.findByInfo(getUnidadeDeMedida(), getCategoria()));
			else
				setListaGrao(repo.findAll());
			
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaGrao(null);
		}
	}

}
