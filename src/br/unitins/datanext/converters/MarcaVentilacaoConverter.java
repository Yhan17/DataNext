package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.MarcaVentilacao;
import br.unitins.datanext.repository.MarcaVentilacaoRepository;

@FacesConverter(forClass = MarcaVentilacao.class)
public class MarcaVentilacaoConverter implements Converter<MarcaVentilacao>{

	@Override
	public MarcaVentilacao getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		MarcaVentilacaoRepository repo = new MarcaVentilacaoRepository();
		MarcaVentilacao marcaVentilacao = repo.findById(Integer.valueOf(value.trim()));
		return marcaVentilacao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, MarcaVentilacao value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
