package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.Ventilacao;
import br.unitins.datanext.repository.VentilacaoRepository;

@FacesConverter(forClass = Ventilacao.class)
public class VentilacaoConverter implements Converter<Ventilacao>{
	@Override
	public Ventilacao getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		VentilacaoRepository repo = new VentilacaoRepository();
		Ventilacao ventilacao = repo.findById(Integer.valueOf(value.trim()));
		return ventilacao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Ventilacao value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}
}
