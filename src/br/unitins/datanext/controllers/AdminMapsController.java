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
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.ArmazenarGrao;
import br.unitins.datanext.repository.ArmazemRepository;

@Named("adminMapsController")
@ViewScoped
public class AdminMapsController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private MapModel maps;
	public List<Armazem> armazem;
	  
    @PostConstruct
    public void init() {
    	maps = new DefaultMapModel();
          
    	
        //Shared coordinates
//        LatLng coord1 = new LatLng(36.879466, 30.667648);
//        LatLng coord2 = new LatLng(36.883707, 30.689216);
//        LatLng coord3 = new LatLng(36.879703, 30.706707);
//        LatLng coord4 = new LatLng(36.885233, 30.702323);

          
        //Basic marker
    	if(!getArmazem().isEmpty()) {
    		for (int i = 0; i < getArmazem().size(); i++) {
    			maps.addOverlay(new Marker(new LatLng(Double.valueOf(getArmazem().get(i).getLocalizacao().getLatitude()),Double.valueOf(getArmazem().get(i).getLocalizacao().getLongitude())), getArmazem().get(i).getSigla()));
				
			}
    		
    	}
//        maps.addOverlay(new Marker(coord2, "Ataturk Parki"));
//        maps.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
//        maps.addOverlay(new Marker(coord4, "Kaleici"));
    }
  
    public List<Armazem> getArmazem() {
    	if(armazem == null) {
    		ArmazemRepository repo = new ArmazemRepository();
    		try {
    			setArmazem(repo.findLocation());
			} catch (RepositoryException e) {
				e.printStackTrace();
				setArmazem(new ArrayList<Armazem>());
			}
    	}
		return armazem;
	}

	public void setArmazem(List<Armazem> armazem) {
		this.armazem = armazem;
	}

	public MapModel getMaps() {
        return maps;
    }
}
