package br.unitins.datanext.controllers.listing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.PlanoDeCusto;
import br.unitins.datanext.repository.PlanoDeCustoRepository;
@Named("planoDeContasListing")
@ViewScoped
public class PlanoContasListing extends Listing<PlanoDeCusto>{

	private static final long serialVersionUID = -9011762519094386462L;
	private String filtro;
	private List<PlanoDeCusto> list;

	public PlanoContasListing() {
		super("planodecustolisting", new PlanoDeCustoRepository());
	}
	
	public void pesquisar() {
		PlanoDeCustoRepository repo = (PlanoDeCustoRepository)getRepository();
		try {
			setList(repo.findByDesc(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<PlanoDeCusto>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<PlanoDeCusto> getList() {
		return list;
	}

	public void setList(List<PlanoDeCusto> list) {
		this.list = list;
	}
	
	
}
