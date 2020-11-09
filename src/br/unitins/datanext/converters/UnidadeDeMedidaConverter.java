package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.UnidadeDeMedida;
import br.unitins.datanext.repository.UnidadeDeMedidaRepository;

@FacesConverter(forClass = UnidadeDeMedida.class)
public class UnidadeDeMedidaConverter implements Converter<UnidadeDeMedida>{

	@Override
	public UnidadeDeMedida getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		UnidadeDeMedidaRepository repo = new UnidadeDeMedidaRepository();
		UnidadeDeMedida unidadeDeMedida = repo.findById(Integer.valueOf(value.trim()));
		return unidadeDeMedida;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, UnidadeDeMedida value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
