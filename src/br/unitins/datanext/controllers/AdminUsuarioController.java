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
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.Caminhao;
import br.unitins.datanext.models.Cidade;
import br.unitins.datanext.models.Estado;
import br.unitins.datanext.models.MarcaCaminhao;
import br.unitins.datanext.models.Motorista;
import br.unitins.datanext.models.Pessoa;
import br.unitins.datanext.models.Usuario;
import br.unitins.datanext.repository.CidadeRepository;
import br.unitins.datanext.repository.MotoristaRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.UsuarioRepository;

@Named("adminUsuarioController")
@ViewScoped
public class AdminUsuarioController extends Controller<Usuario> {

	private static final long serialVersionUID = 4920291533243512954L;
	private List<Usuario> listaUsuario;

	public AdminUsuarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashUsuario");
		entity = (Usuario) flash.get("flashUsuario");
	}

	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = new Usuario();

			entity.setPessoa(new Pessoa());
			entity.getPessoa().setCidade(new Cidade());
			entity.getPessoa().getCidade().setEstado(new Estado());
		}
		return entity;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
			EntityManager em = JPAUtil.getEntityManager();
			UsuarioRepository repo = new UsuarioRepository();
			try {
				setListaUsuario(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaUsuario(null);
			}
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaMotorista) {
		this.listaUsuario = listaMotorista;
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
//			Util.addInfoMessage("Operação realizada com sucesso.");
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
		return "usuarioForm.xhtml?faces-redirect=true";
	}

	@Override
	public String editar(Usuario entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashUsuario", entity);
		return "usuarioForm.xhtml?faces-redirect=true";
	}

	@Override
	public void excluir(Usuario obj) {
		super.excluir(obj);
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioRepository repo = new UsuarioRepository();
		try {
			setListaUsuario(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaUsuario(null);
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

}
