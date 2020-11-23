package br.unitins.datanext.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.datanext.models.StatusArmazem;

@Converter(autoApply = true)
public class StatusArmazemConverter implements AttributeConverter<StatusArmazem, Integer>{

	@Override
	public Integer convertToDatabaseColumn(StatusArmazem attribute) {
		return attribute == null? null : attribute.getId();

	}

	@Override
	public StatusArmazem convertToEntityAttribute(Integer dbData) {
		return StatusArmazem.valueOf(dbData);
	}

}
