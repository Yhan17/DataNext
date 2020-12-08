package br.unitins.datanext.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.catalina.core.ApplicationPart;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.Agricultor;
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.Cidade;
import br.unitins.datanext.models.Estado;
import br.unitins.datanext.models.Pessoa;
import br.unitins.datanext.repository.AgricultorRepository;
import br.unitins.datanext.repository.CidadeRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.UnidadeDeMedidaRepository;

@Named("adminAgricultorController")
@ViewScoped
public class AdminAgricultorController extends Controller<Agricultor> {

	private static final long serialVersionUID = 4920291533243512954L;
	private List<Agricultor> listaAgricultor;
	private ApplicationPart imagem;
	private String filtro;
	public ApplicationPart getImagem() {
		return imagem;
	}

	public void setImagem(ApplicationPart imagem) {
		this.imagem = imagem;
	}

	public AdminAgricultorController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashAgricultor");
		entity = (Agricultor) flash.get("flashAgricultor");	
	}
	
	@Override
	public Agricultor getEntity() {
		if (entity == null) {
			entity = new Agricultor();
			entity.setPessoa(new Pessoa());
			entity.getPessoa().setCidade(new Cidade());
			entity.getPessoa().getCidade().setEstado(new Estado());
		}
		return entity;
	}

	public List<Agricultor> getListaAgricultor() {
		if(listaAgricultor == null) {
			listaAgricultor = new ArrayList<Agricultor>();
			EntityManager em = JPAUtil.getEntityManager();
			AgricultorRepository repo = new AgricultorRepository();
			try {
				setListaAgricultor(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaAgricultor(null);
			}
		}
		return listaAgricultor;
	}

	public void setListaAgricultor(List<Agricultor> listaAgricultor) {
		this.listaAgricultor = listaAgricultor;
	}
	
	@Override
	public String salvar() {
		Repository<Agricultor> repo = new Repository<Agricultor>();
		String caminhoImagem = "";
		if (imagem != null && imagem.getSubmittedFileName() != null) {
			caminhoImagem = "C:\\Users\\shika\\OneDrive\\Documentos\\Eclipse\\Workspace\\Dev-Server2\\DataNext\\WebContent\\uploads\\agricultor\\" + imagem.getSubmittedFileName();
			

			try {
				if(getEntity().getImagem() != null) {
					File del = new File(getEntity().getImagem());
					del.delete();
					del = null;
				}
				// cria um espaço de memória que vai armazenar o conteúdo da imagem PORQUE A IMAGEM é UM ARRAY DE BYTES
				byte[] bytesImagem = new byte[(int) imagem.getSize()];
				// lê o conteudo da imagem e joga dentro do array de bytes
				imagem.getInputStream().read(bytesImagem);
				// cria uma referência para o arquivo que será criado no lado do server
				File f = new File(caminhoImagem);
				// cria o objeto que irá manipular o arquivo criado
				FileOutputStream fos = new FileOutputStream(f);
				// escreve o conteúdo da imagem (upload) dentro do arquivo no servidor
				fos.write(bytesImagem);

				fos.close();
				
				getEntity().setImagem(caminhoImagem);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
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
			return "agricultor.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			if(getEntity().getImagem() != null) {
				File del = new File(getEntity().getImagem());
				del.delete();
				del = null;
			}
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "agricultorForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(Agricultor entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashAgricultor", entity);
		return "agricultorForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public void excluir(Agricultor obj) {
		super.excluir(obj);
		File file = new File(obj.getImagem());
		file.delete();
		EntityManager em = JPAUtil.getEntityManager();
		AgricultorRepository repo = new AgricultorRepository();
		try {
			setListaAgricultor(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaAgricultor(null);
		}
		
	}
	
	public String trocarStatus(Agricultor obj) {
		Repository<Agricultor> repo = new Repository<Agricultor>();
		if(obj.getStatus())
			obj.setStatus(false);
		else
			obj.setStatus(true);
		try {
			repo.beginTransaction();
			repo.save(obj);
			repo.commitTransaction();
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage("Inserção Realizada com Sucesso!"));
					
					
					FacesContext.getCurrentInstance()
						    .getExternalContext()
						    .getFlash().setKeepMessages(true);
//			Util.addInfoMessage("Operação realizada com sucesso.");
			return "agricultor.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "agricultorForm.xhtml?faces-redirect=true";
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
		AgricultorRepository repo = new AgricultorRepository();
		try {
			setListaAgricultor(repo.findByInfo(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaAgricultor(null);
		}
	}

}
