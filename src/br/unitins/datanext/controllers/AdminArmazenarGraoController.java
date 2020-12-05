package br.unitins.datanext.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.Agricultor;
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.ArmazenarGrao;
import br.unitins.datanext.models.Endereco;
import br.unitins.datanext.models.EstadoDoGrao;
import br.unitins.datanext.models.Grao;
import br.unitins.datanext.models.Motorista;
import br.unitins.datanext.repository.AgricultorRepository;
import br.unitins.datanext.repository.ArmazemRepository;
import br.unitins.datanext.repository.ArmazenarGraoRepository;
import br.unitins.datanext.repository.GraoRepository;
import br.unitins.datanext.repository.MotoristaRepository;
import br.unitins.datanext.repository.Repository;

@Named("adminArmazenarGraoController")
@ViewScoped
public class AdminArmazenarGraoController extends Controller<ArmazenarGrao> {

	private static final long serialVersionUID = -6046574031018542074L;
	private List<ArmazenarGrao> listaArmazenarGrao;
	private List<Grao> listaGrao;
	private List<Agricultor> listaAgricultor;
	private List<Armazem> listaArmazem;
	private List<Motorista> listaMotorista;
	private DefaultDiagramModel model;

	@Override
	public ArmazenarGrao getEntity() {
		if (entity == null) {
			entity = new ArmazenarGrao();
			entity.setAgricultor(new Agricultor());
			entity.setArmazem(new Armazem());
			entity.setGrao(new Grao());
			entity.setMotorista(new Motorista());
			entity.setEndereco(new Endereco());
		}
		return entity;
	}

	@PostConstruct
	public void init() {
		model = new DefaultDiagramModel();
		model.setMaxConnections(-1);
		model.setConnectionsDetachable(false);

		if (getEntity().getEstadoDoGrao() != null) {
			if (getEntity().getUmidadeRelativaDoAr() == null && getEntity().getTemperatura() == null
					&& getEntity().getPressaoVaporAr() == null && getEntity().getPressaoVaporGrao() == null) {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				Util.addErrorMessage("Preencha todos os campos para gerar o fluxograma");
			} else {
//				Campos Automáticos
//				Tipo Atmazenagem
				if (getEntity().getUmidadeRelativaDoAr() + getEntity().getTemperatura() < 55.5) {
					getEntity().setCondicaoArmazenagem("ARMAZENAMENTO SEGURO");
				} else {
					getEntity().setCondicaoArmazenagem("RISCO DE ARMAZENAGEM");
				}
//				Situação Atual ou necessidade do Grão
				if (getEntity().getPressaoVaporGrao() > getEntity().getPressaoVaporAr()) {
					getEntity().setSituacaoGrao("SECAGEM *");
				} else if (getEntity().getPressaoVaporGrao() < getEntity().getPressaoVaporAr()) {
					getEntity().setSituacaoGrao("UMEDECIMENTO *");
				} else {
					getEntity().setSituacaoGrao("EQUILÍBRIO HIGROSCÓPICO *");
				}
//				Geração FluxoGrama
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("FluxoGrama Gerado"));
				Element elementA = new Element("Produto Úmido e Limpo", "20em", "6em");
				elementA.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
				elementA.setDraggable(false);
				Element elementB = new Element("Produto Seco e Sujo", "20em", "6em");
				elementB.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
				elementB.setDraggable(false);
				Element elementC = new Element("Produto Úmido e Sujo", "20em", "6em");
				elementC.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
				elementC.setDraggable(false);

				Element elementD = new Element("Produto Seco e Limpo", "20em", "6em");
				elementD.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
				elementD.setDraggable(false);

				Element elementE = new Element("Pré-Limpeza", "40em", "12em");
				elementE.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
				elementE.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
				elementE.setDraggable(false);

				Element elementF = new Element("Secador", "60em", "12em");
				elementF.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
				elementF.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
				elementF.setDraggable(false);

				Element elementG = new Element("Armazenagem", "80em", "6em");
				elementG.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
				elementG.setDraggable(false);

				if (getEntity().getEstadoDoGrao().getLabel().equals("Umido e Limpo")) {
					model.addElement(elementA);
					model.addElement(elementF);
					model.addElement(elementG);
					model.connect(new Connection(elementA.getEndPoints().get(0), elementF.getEndPoints().get(0)));
					model.connect(new Connection(elementF.getEndPoints().get(1), elementG.getEndPoints().get(0)));
					getEntity().setEtapaArmazenamento("Secador -> Armazenagem");
				}

				if (getEntity().getEstadoDoGrao().getLabel().equals("Seco e Sujo")) {
					model.addElement(elementB);
					model.addElement(elementE);
					model.addElement(elementG);

					model.connect(new Connection(elementB.getEndPoints().get(0), elementE.getEndPoints().get(0)));
					model.connect(new Connection(elementE.getEndPoints().get(1), elementG.getEndPoints().get(0)));
					getEntity().setEtapaArmazenamento("Pré-Limpeza -> Armazenagem");

				}

				if (getEntity().getEstadoDoGrao().getLabel().equals("Umido e Sujo")) {
					model.addElement(elementC);
					model.addElement(elementE);
					model.addElement(elementF);
					model.addElement(elementG);

					model.connect(new Connection(elementC.getEndPoints().get(0), elementE.getEndPoints().get(0)));
					model.connect(new Connection(elementE.getEndPoints().get(1), elementF.getEndPoints().get(0)));
					model.connect(new Connection(elementF.getEndPoints().get(1), elementG.getEndPoints().get(0)));
					getEntity().setEtapaArmazenamento("Pré-Limpeza -> Secador -> Armazenagem");

				}
				if (getEntity().getEstadoDoGrao().getLabel().equals("Seco e Limpo")) {
					model.addElement(elementD);
					model.addElement(elementG);

					model.connect(new Connection(elementD.getEndPoints().get(0), elementG.getEndPoints().get(0)));
					getEntity().setEtapaArmazenamento("Armazenagem");

				}

			}
		}

	}

	public List<ArmazenarGrao> getListaArmazenarGrao() {
		if (listaArmazenarGrao == null) {
			ArmazenarGraoRepository repo = new ArmazenarGraoRepository();
			try {
				setListaArmazenarGrao(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setListaArmazenarGrao(new ArrayList<ArmazenarGrao>());
			}
		}
		return listaArmazenarGrao;
	}

	public void setListaArmazenarGrao(List<ArmazenarGrao> listaArmazenarGrao) {
		this.listaArmazenarGrao = listaArmazenarGrao;
	}

	public EstadoDoGrao[] getListaEstadoDoGrao() {
		return EstadoDoGrao.values();
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

	public DefaultDiagramModel getModel() {
		return model;
	}

	public void setModel(DefaultDiagramModel model) {
		this.model = model;
	}

	@Override
	public String salvar() {
		Repository<ArmazenarGrao> repo = new Repository<ArmazenarGrao>();
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserção Realizada com Sucesso!"));

			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//			Util.addInfoMessage("Operação realizada com sucesso.");
			return "armazenarGraos.xhtml?faces-redirect=true";
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			Util.addErrorMessage("Erro ao Salvar.");
		}

		limpar();
		return "armazenarGraos.xhtml?faces-redirect=true";
	}

	public void generateRelatorio(ArmazenarGrao obj) {
		Util.redirect("/DataNext/armazenarreport?ID_ARMAZENAGEM=" + obj.getId());

	}
}
