package br.unitins.datanext.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.datanext.models.MarcaCaminhao;

@Converter(autoApply = true)
public class MarcaCaminhaoConverter implements AttributeConverter<MarcaCaminhao, Integer> {

	@Override
	public Integer convertToDatabaseColumn(MarcaCaminhao attribute) {
		return attribute == null? null : attribute.getId();
	}

	@Override
	public MarcaCaminhao convertToEntityAttribute(Integer dbData) {
		return MarcaCaminhao.valueOf(dbData);
	}

}
