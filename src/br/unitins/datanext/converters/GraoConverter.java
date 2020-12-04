package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.Grao;
import br.unitins.datanext.repository.GraoRepository;

@FacesConverter(forClass = Grao.class)
public class GraoConverter implements Converter<Grao>{

	@Override
	public Grao getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		GraoRepository repo = new GraoRepository();
		Grao grao = repo.findById(Integer.valueOf(value.trim()));
		return grao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Grao value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
