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
import br.unitins.datanext.models.Ventilacao;
import br.unitins.datanext.repository.MarcaVentilacaoRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.UnidadeDeMedidaRepository;
import br.unitins.datanext.repository.VentilacaoRepository;

@Named("adminVentilacaoController")
@ViewScoped
public class AdminVentilacaoController extends Controller<Ventilacao> {

	private static final long serialVersionUID = -1559751125740756306L;
	private String filtro;
	private List<Ventilacao> listaVentilacao;
	private List<MarcaVentilacao> listaMarca;
	public AdminVentilacaoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashVentilacao");
		entity = (Ventilacao) flash.get("flashVentilacao");	
	}

	@Override
	public Ventilacao getEntity() {
		if (entity == null) {
			entity = new Ventilacao();
			entity.setMarca(new MarcaVentilacao());
		}
		return entity;
	}
	
	@Override
	public String salvar() {
		Repository<Ventilacao> repo = new Repository<Ventilacao>();
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
			return "ventilacao.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "ventilacaoForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(Ventilacao entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashVentilacao", entity);
		return "ventilacaoForm.xhtml?faces-redirect=true";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public void excluir(Ventilacao obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		VentilacaoRepository repo = new VentilacaoRepository();
		try {
			setListaVentilacao(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaVentilacao(null);
		}
		
	}
	
	public List<Ventilacao> getListaVentilacao() {
		if(listaVentilacao == null) {
			listaVentilacao = new ArrayList<Ventilacao>();
			EntityManager em = JPAUtil.getEntityManager();
			VentilacaoRepository repo = new VentilacaoRepository();
			try {
				setListaVentilacao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaVentilacao(null);
			}
		}
		return listaVentilacao;
	}

	public void setListaVentilacao(List<Ventilacao> listaVentilacao) {
		this.listaVentilacao = listaVentilacao;
	}

	public List<MarcaVentilacao> getListaMarca() {
		if (listaMarca == null) {
			MarcaVentilacaoRepository repo = new MarcaVentilacaoRepository();
			try {
				setListaMarca(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaMarca(new ArrayList<MarcaVentilacao>());
			}
		}
		return listaMarca;
	}

	public void setListaMarca(List<MarcaVentilacao> listaMarca) {
		this.listaMarca = listaMarca;
	}

}
