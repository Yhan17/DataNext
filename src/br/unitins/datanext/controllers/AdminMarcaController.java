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
import br.unitins.datanext.repository.MarcaVentilacaoRepository;
import br.unitins.datanext.repository.Repository;

@Named("adminMarcaController")
@ViewScoped
public class AdminMarcaController extends Controller<MarcaVentilacao> {

	private static final long serialVersionUID = -1559751125740756306L;
	private String filtro;
	private List<MarcaVentilacao> listaMarcaVentilacao;
	
	public AdminMarcaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashMarca");
		entity = (MarcaVentilacao) flash.get("flashMarca");	
	}

	@Override
	public MarcaVentilacao getEntity() {
		if (entity == null) {
			entity = new MarcaVentilacao();
		}
		return entity;
	}
	
	@Override
	public String salvar() {
		Repository<MarcaVentilacao> repo = new Repository<MarcaVentilacao>();
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
			return "marca.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "marcaForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(MarcaVentilacao entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashMarca", entity);
		return "marcaForm.xhtml?faces-redirect=true";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public void excluir(MarcaVentilacao obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		MarcaVentilacaoRepository repo = new MarcaVentilacaoRepository();
		try {
			setListaMarcaVentilacao(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaMarcaVentilacao(null);
		}
		
	}
	
	public List<MarcaVentilacao> getListaMarcaVentilacao() {
		if(listaMarcaVentilacao == null) {
			listaMarcaVentilacao = new ArrayList<MarcaVentilacao>();
			EntityManager em = JPAUtil.getEntityManager();
			MarcaVentilacaoRepository repo = new MarcaVentilacaoRepository();
			try {
				setListaMarcaVentilacao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaMarcaVentilacao(null);
			}
		}
		return listaMarcaVentilacao;
	}

	public void setListaMarcaVentilacao(List<MarcaVentilacao> listaMarcaVentilacao) {
		this.listaMarcaVentilacao = listaMarcaVentilacao;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		MarcaVentilacaoRepository repo = new MarcaVentilacaoRepository();
		try {
			setListaMarcaVentilacao(repo.findByMarca(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaMarcaVentilacao(null);
		}
	}

}
