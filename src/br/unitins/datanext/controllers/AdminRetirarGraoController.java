package br.unitins.datanext.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.Agricultor;
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.Endereco;
import br.unitins.datanext.models.Grao;
import br.unitins.datanext.models.Motorista;
import br.unitins.datanext.models.RetirarGrao;
import br.unitins.datanext.repository.AgricultorRepository;
import br.unitins.datanext.repository.ArmazemRepository;
import br.unitins.datanext.repository.GraoRepository;
import br.unitins.datanext.repository.MotoristaRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.RetirarGraoRepository;

@Named("adminRetirarGraoController")
@ViewScoped
public class AdminRetirarGraoController extends Controller<RetirarGrao> {

	private static final long serialVersionUID = 2105917119483518197L;
	private List<RetirarGrao> listaRetirarGrao;
	private List<Grao> listaGrao;
	private List<Agricultor> listaAgricultor;
	private List<Armazem> listaArmazem;
	private List<Motorista> listaMotorista;

	@Override
	public RetirarGrao getEntity() {
		if (entity == null) {
			entity = new RetirarGrao();
			entity.setAgricultor(new Agricultor());
			entity.setArmazem(new Armazem());
			entity.setGrao(new Grao());
			entity.setMotorista(new Motorista());
			entity.setEndereco(new Endereco());
		}
		return entity;
	}

	public List<RetirarGrao> getListaRetirarGrao() {
		if (listaRetirarGrao == null) {
			RetirarGraoRepository repo = new RetirarGraoRepository();
			try {
				setListaRetirarGrao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaRetirarGrao(new ArrayList<RetirarGrao>());
			}
		}		
		return listaRetirarGrao;
	}

	public void setListaRetirarGrao(List<RetirarGrao> listaRetirarGrao) {
		this.listaRetirarGrao = listaRetirarGrao;
	}

	public List<Grao> getListaGrao() {
		if (listaGrao == null) {
			GraoRepository repo = new GraoRepository();
			try {
				setListaGrao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaGrao(new ArrayList<Grao>());
			}
		}		
		return listaGrao;
	}

	public void setListaGrao(List<Grao> listaGrao) {
		this.listaGrao = listaGrao;
	}

	public List<Agricultor> getListaAgricultor() {
		if (listaAgricultor == null) {
			AgricultorRepository repo = new AgricultorRepository();
			try {
				setListaAgricultor(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaAgricultor(new ArrayList<Agricultor>());
			}
		}		
		return listaAgricultor;
	}

	public void setListaAgricultor(List<Agricultor> listaAgricultor) {
		this.listaAgricultor = listaAgricultor;
	}

	public List<Armazem> getListaArmazem() {
		if (listaArmazem == null) {
			ArmazemRepository repo = new ArmazemRepository();
			try {
				setListaArmazem(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaArmazem(new ArrayList<Armazem>());
			}
		}
		return listaArmazem;
	}

	public void setListaArmazem(List<Armazem> listaArmazem) {
		this.listaArmazem = listaArmazem;
	}

	public List<Motorista> getListaMotorista() {
		if (listaMotorista == null) {
			MotoristaRepository repo = new MotoristaRepository();
			try {
				setListaMotorista(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaMotorista(new ArrayList<Motorista>());
			}
		}
		return listaMotorista;
	}

	public void setListaMotorista(List<Motorista> listaMototrista) {
		this.listaMotorista = listaMototrista;
	}
	

	@Override
	public String salvar() {
		Repository<RetirarGrao> repo = new Repository<RetirarGrao>();
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
			return "retirarGraos.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "retirarGraos.xhtml?faces-redirect=true";
	}

}
