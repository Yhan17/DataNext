package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.repository.ArmazemRepository;


@FacesConverter(forClass = Armazem.class)
public class ArmazemConverter implements Converter<Armazem> {

	@Override
	public Armazem getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		ArmazemRepository repo = new ArmazemRepository();
		Armazem armazem = repo.findById(Integer.valueOf(value.trim()));
		return armazem;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Armazem value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
