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
import br.unitins.datanext.models.Caminhao;
import br.unitins.datanext.models.Cidade;
import br.unitins.datanext.models.Estado;
import br.unitins.datanext.models.MarcaCaminhao;
import br.unitins.datanext.models.Motorista;
import br.unitins.datanext.models.Pessoa;
import br.unitins.datanext.repository.AgricultorRepository;
import br.unitins.datanext.repository.CidadeRepository;
import br.unitins.datanext.repository.MotoristaRepository;
import br.unitins.datanext.repository.Repository;

@Named("adminMotoristaController")
@ViewScoped
public class AdminMotoristaController extends Controller<Motorista> {

	private static final long serialVersionUID = 4920291533243512954L;
	private List<Motorista> listaMotorista;
	private String filtro;
	public AdminMotoristaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashMotorista");
		entity = (Motorista) flash.get("flashMotorista");	
	}
	
	@Override
	public Motorista getEntity() {
		if (entity == null) {
			entity = new Motorista();
			entity.setCaminhao(new Caminhao());
			entity.setPessoa(new Pessoa());
			entity.getPessoa().setCidade(new Cidade());
			entity.getPessoa().getCidade().setEstado(new Estado());
		}
		return entity;
	}

	public List<Motorista> getListaMotorista() {
		if(listaMotorista == null) {
			listaMotorista = new ArrayList<Motorista>();
			EntityManager em = JPAUtil.getEntityManager();
			MotoristaRepository repo = new MotoristaRepository();
			try {
				setListaMotorista(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaMotorista(null);
			}
		}
		return listaMotorista;
	}

	public void setListaMotorista(List<Motorista> listaMotorista) {
		this.listaMotorista = listaMotorista;
	}
	
	@Override
	public String salvar() {
		Repository<Motorista> repo = new Repository<Motorista>();
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
			return "motorista.xhtml?faces-redirect=true";
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
		return "motoristaForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(Motorista entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashMotorista", entity);
		return "motoristaForm.xhtml?faces-redirect=true";
	}
	
	public MarcaCaminhao[] getListaMarcaCaminhao() {
		return MarcaCaminhao.values();
	}
	
	@Override
	public void excluir(Motorista obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		MotoristaRepository repo = new MotoristaRepository();
		try {
			setListaMotorista(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaMotorista(null);
		}
		
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
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		MotoristaRepository repo = new MotoristaRepository();
		try {
			setListaMotorista(repo.findByInfo(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaMotorista(null);
		}
	}
}
