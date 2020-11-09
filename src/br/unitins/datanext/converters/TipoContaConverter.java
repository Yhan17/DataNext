package br.unitins.datanext.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.datanext.models.TipoConta;

@Converter(autoApply = true)
public class TipoContaConverter implements AttributeConverter<TipoConta, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoConta attribute) {
		return attribute == null? null : attribute.getId();
	}

	@Override
	public TipoConta convertToEntityAttribute(Integer dbData) {
		return TipoConta.valueOf(dbData);
	}

}
