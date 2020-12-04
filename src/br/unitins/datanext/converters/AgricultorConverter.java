package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.Agricultor;
import br.unitins.datanext.repository.AgricultorRepository;

@FacesConverter(forClass = Agricultor.class)
public class AgricultorConverter implements Converter<Agricultor> {

	@Override
	public Agricultor getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		AgricultorRepository repo = new AgricultorRepository();
		Agricultor agricultor = repo.findById(Integer.valueOf(value.trim()));
		return agricultor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Agricultor value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
