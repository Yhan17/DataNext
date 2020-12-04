package br.unitins.datanext.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.datanext.models.EstadoDoGrao;
@Converter(autoApply = true)
public class EstadoDoGraoConverter implements AttributeConverter<EstadoDoGrao, Integer>{

	@Override
	public Integer convertToDatabaseColumn(EstadoDoGrao attribute) {
		return attribute == null? null : attribute.getId();

	}

	@Override
	public EstadoDoGrao convertToEntityAttribute(Integer dbData) {
		return EstadoDoGrao.valueOf(dbData);
	}

}
