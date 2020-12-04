package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.Motorista;
import br.unitins.datanext.repository.MotoristaRepository;

@FacesConverter(forClass = Motorista.class)
public class MotoristaConverter implements Converter<Motorista>{

	@Override
	public Motorista getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		MotoristaRepository repo = new MotoristaRepository();
		Motorista motorista = repo.findById(Integer.valueOf(value.trim()));
		return motorista;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Motorista value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
