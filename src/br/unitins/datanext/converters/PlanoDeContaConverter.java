package br.unitins.datanext.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.datanext.models.PlanoDeCusto;
import br.unitins.datanext.repository.PlanoDeCustoRepository;

@FacesConverter(forClass = PlanoDeCusto.class)
public class PlanoDeContaConverter  implements Converter<PlanoDeCusto> {

	@Override
	public PlanoDeCusto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		PlanoDeCustoRepository repo = new PlanoDeCustoRepository();
		PlanoDeCusto marcaVentilacao = repo.findById(Integer.valueOf(value.trim()));
		return marcaVentilacao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, PlanoDeCusto value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
