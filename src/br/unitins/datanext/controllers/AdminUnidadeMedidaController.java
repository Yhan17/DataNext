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
import br.unitins.datanext.models.MarcaVentilacao;
import br.unitins.datanext.models.UnidadeDeMedida;
import br.unitins.datanext.repository.MarcaVentilacaoRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.UnidadeDeMedidaRepository;

@Named("adminUnidadeMedidaController")
@ViewScoped
public class AdminUnidadeMedidaController extends Controller<UnidadeDeMedida> {

	private static final long serialVersionUID = -3013768893328582676L;
	private String filtro;
	private List<UnidadeDeMedida> listaUnidadeDeMedida;
	
	public AdminUnidadeMedidaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashUnidadeDeMedida");
		entity = (UnidadeDeMedida) flash.get("flashUnidadeDeMedida");	
	}
	
	@Override
	public UnidadeDeMedida getEntity() {
		if (entity == null) {
			entity = new UnidadeDeMedida();
		}
		return entity;
	}
	@Override
	public String salvar() {
		Repository<UnidadeDeMedida> repo = new Repository<UnidadeDeMedida>();
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
			return "unidadedemedida.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "unidadedemedidaForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(UnidadeDeMedida entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashUnidadeDeMedida", entity);
		return "unidadedemedidaForm.xhtml?faces-redirect=true";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public void excluir(UnidadeDeMedida obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		UnidadeDeMedidaRepository repo = new UnidadeDeMedidaRepository();
		try {
			setListaUnidadeDeMedida(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaUnidadeDeMedida(null);
		}
		
	}
	
	public List<UnidadeDeMedida> getListaUnidadeDeMedida() {
		if(listaUnidadeDeMedida == null) {
			listaUnidadeDeMedida = new ArrayList<UnidadeDeMedida>();
			EntityManager em = JPAUtil.getEntityManager();
			UnidadeDeMedidaRepository repo = new UnidadeDeMedidaRepository();
			try {
				setListaUnidadeDeMedida(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaUnidadeDeMedida(null);
			}
		}
		return listaUnidadeDeMedida;
	}

	public void setListaUnidadeDeMedida(List<UnidadeDeMedida> listaMarcaVentilacao) {
		this.listaUnidadeDeMedida = listaMarcaVentilacao;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		UnidadeDeMedidaRepository repo = new UnidadeDeMedidaRepository();
		try {
			setListaUnidadeDeMedida(repo.findByUn(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaUnidadeDeMedida(null);
		}
	}
}
