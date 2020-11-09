package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.Categoria;
import br.unitins.datanext.repository.CategoriaRepository;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria>{

	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		CategoriaRepository repo = new CategoriaRepository();
		Categoria categoria = repo.findById(Integer.valueOf(value.trim()));
		return categoria;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Categoria value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
