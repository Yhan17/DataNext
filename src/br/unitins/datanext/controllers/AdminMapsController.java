package br.unitins.datanext.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.repository.ArmazemRepository;

@Named("adminMapsController")
@ViewScoped
public class AdminMapsController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private MapModel maps;
	private List<Object[]> armazem;
	  
    @PostConstruct
    public void init() {
    	maps = new DefaultMapModel();
          
    	
    	if(!getArmazem().isEmpty()) {
    		for (int i = 0; i < getArmazem().size(); i++) {
    			System.out.println(getArmazem().get(i)[0]);
    			System.out.println(getArmazem().get(i)[1]);
    			System.out.println(getArmazem().get(i)[2]);
    	        LatLng coord = new LatLng(Double.parseDouble((String) getArmazem().get(i)[1]),Double.parseDouble((String) getArmazem().get(i)[2]));
    			maps.addOverlay(new Marker(coord, (String) getArmazem().get(i)[0]));
    			coord = null;
				
			}
    		
    	}

    }
  
    public List<Object[]> getArmazem() {
    	if(armazem == null) {
    		ArmazemRepository repo = new ArmazemRepository();
    		try {
    			setArmazem(repo.findLocation());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setArmazem(new ArrayList<Object[]>());
			}
    	}
		return armazem;
	}

	public void setArmazem(List<Object[]> armazem) {
		this.armazem = armazem;
	}

	public MapModel getMaps() {
        return maps;
    }
}
