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
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.Localizacao;
import br.unitins.datanext.models.MarcaVentilacao;
import br.unitins.datanext.models.Ventilacao;
import br.unitins.datanext.repository.AgricultorRepository;
import br.unitins.datanext.repository.ArmazemRepository;
import br.unitins.datanext.repository.Repository;
import br.unitins.datanext.repository.VentilacaoRepository;

@Named("adminArmazemController")
@ViewScoped
public class AdminArmazemController extends Controller<Armazem> {

	private static final long serialVersionUID = 8156123863834379899L;
	private List<Armazem> listaArmazem;
	private ApplicationPart imagem;
	private List<Ventilacao> listaVentilacao;
	private String filtro;
	public ApplicationPart getImagem() {
		return imagem;
	}

	public void setImagem(ApplicationPart imagem) {
		this.imagem = imagem;
	}

	public AdminArmazemController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashArmazem");
		entity = (Armazem) flash.get("flashArmazem");	
	}
	
	@Override
	public Armazem getEntity() {
		if (entity == null) {
			entity = new Armazem();
			entity.setLocalizacao(new Localizacao());
			entity.setVentilacao(new Ventilacao());
			entity.getVentilacao().setMarca(new MarcaVentilacao());
		}
		return entity;
	}

	public List<Armazem> getListaArmazem() {
		if(listaArmazem == null) {
			listaArmazem = new ArrayList<Armazem>();
			EntityManager em = JPAUtil.getEntityManager();
			ArmazemRepository repo = new ArmazemRepository();
			try {
				setListaArmazem(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao excluir.");
				setListaArmazem(null);
			}
		}
		return listaArmazem;
	}

	public void setListaArmazem(List<Armazem> listaArmazem) {
		this.listaArmazem = listaArmazem;
	}
	
	
	
	public List<Ventilacao> getListaVentilacao() {
		if(listaVentilacao == null) {
			VentilacaoRepository repo = new VentilacaoRepository();
			try {
				setListaVentilacao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaVentilacao(new ArrayList<Ventilacao>());
			}
		}
		return listaVentilacao;
	}

	public void setListaVentilacao(List<Ventilacao> listaVentilacao) {
		this.listaVentilacao = listaVentilacao;
	}

	@Override
	public String salvar() {
		Repository<Armazem> repo = new Repository<Armazem>();
		String caminhoImagem = "";
		if (imagem != null && imagem.getSubmittedFileName() != null) {
			caminhoImagem = "C:\\Users\\shika\\OneDrive\\Documentos\\Eclipse\\Workspace\\Dev-Server2\\DataNext\\WebContent\\uploads\\armazens\\" + imagem.getSubmittedFileName();
			

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
			return "armazem.xhtml?faces-redirect=true";
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
		return "armazemForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editar(Armazem entity) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("flashArmazem", entity);
		return "armazemForm.xhtml?faces-redirect=true";
	}
	
	@Override
	public void excluir(Armazem obj) {
		super.excluir(obj);
		File file = new File(obj.getImagem());
		file.delete();
		EntityManager em = JPAUtil.getEntityManager();
		ArmazemRepository repo = new ArmazemRepository();
		try {
			setListaArmazem(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaArmazem(null);
		}
		
	}
	
	public String trocarStatus(Armazem obj) {
		Repository<Armazem> repo = new Repository<Armazem>();
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
			return "armazem.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return "armazemForm.xhtml?faces-redirect=true";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void pesquisar() {
		EntityManager em = JPAUtil.getEntityManager();
		ArmazemRepository repo = new ArmazemRepository();
		try {
			setListaArmazem(repo.findByInfo(getFiltro()));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaArmazem(null);
		}
	}
	
}
