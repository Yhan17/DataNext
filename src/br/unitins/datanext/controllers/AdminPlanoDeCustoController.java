package br.unitins.datanext.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.DualListModel;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.PlanoDeCusto;
import br.unitins.datanext.models.TipoConta;
import br.unitins.datanext.repository.PlanoDeCustoRepository;
import br.unitins.datanext.repository.Repository;

@Named("adminPlanoDeCustoController")
@ViewScoped
public class AdminPlanoDeCustoController extends Controller<PlanoDeCusto> {

	private static final long serialVersionUID = -1559751125740756306L;
	private String filtro;
	private List<PlanoDeCusto> listaPlanoDeCusto;
	private List<PlanoDeCusto> listaDependente;
	private DualListModel<PlanoDeCusto> dependentes;


	
	@PostConstruct
	public void init() {
		
		dependentes = new DualListModel<PlanoDeCusto>(getListaDependente(), getEntity().getDependentes());
		getEntity().setDependentes(dependentes.getTarget());
		
	}



	public AdminPlanoDeCustoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashPlano");
		entity = (PlanoDeCusto) flash.get("flashPlano");	

	}
	
	public TipoConta[] getListaTipoConta() {
		return TipoConta.values();
	}

	@Override
	public PlanoDeCusto getEntity() {
		if (entity == null) {
			entity = new PlanoDeCusto();
			entity.setDependentes(new ArrayList<PlanoDeCusto>());
		}
		return entity;
	}
	
	@Override
	public String salvar() {
		Repository<PlanoDeCusto> repo = new Repository<PlanoDeCusto>();
		getEntity().setDependentes(dependentes.getTarget());
		getEntity().getDependentes().get(0);
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
			return "contas.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "contasForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(PlanoDeCusto entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashPlano", entity);
		return "contasForm.xhtml?faces-redirect=true";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public void excluir(PlanoDeCusto obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		PlanoDeCustoRepository repo = new PlanoDeCustoRepository();
		try {
			setListaPlanoDeCusto(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaPlanoDeCusto(null);
		}
		
	}
	
	public List<PlanoDeCusto> getListaPlanoDeCusto() {
		if(listaPlanoDeCusto == null) {
			listaPlanoDeCusto = new ArrayList<PlanoDeCusto>();
			EntityManager em = JPAUtil.getEntityManager();
			PlanoDeCustoRepository repo = new PlanoDeCustoRepository();
			try {
				setListaPlanoDeCusto(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaPlanoDeCusto(null);
			}
		}
		return listaPlanoDeCusto;
	}

	public void setListaPlanoDeCusto(List<PlanoDeCusto> listaMarcaVentilacao) {
		this.listaPlanoDeCusto = listaMarcaVentilacao;
	}

	
	
	public List<PlanoDeCusto> getListaDependente() {
		if (listaDependente == null) {
			PlanoDeCustoRepository repo = new PlanoDeCustoRepository();
			try {
				setListaDependente(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaDependente(new ArrayList<PlanoDeCusto>());
			}
		}
		return listaDependente;
	}

	public void setListaDependente(List<PlanoDeCusto> listaDependente) {
		this.listaDependente = listaDependente;
	}

	public DualListModel<PlanoDeCusto> getDependentes() {
		return dependentes;
	}

	public void setDependentes(DualListModel<PlanoDeCusto> dependentes) {
		this.dependentes = dependentes;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		PlanoDeCustoRepository repo = new PlanoDeCustoRepository();
		try {
			setListaPlanoDeCusto(repo.findByMarca(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaPlanoDeCusto(null);
		}
	}

}
