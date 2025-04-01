package br.com.marcielli.DigBank360.helpers.converters;

import java.util.Arrays;

import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
//public class TipoDeContaConverter implements AttributeConverter<TipoDeConta, String> {
//
//	@Override
//	public String convertToDatabaseColumn(TipoDeConta tipoDeConta) {
//		if(tipoDeConta == null) {
//			return null;
//		}
//		return tipoDeConta.getDescricao();
//	}
//
//	@Override
//	public TipoDeConta convertToEntityAttribute(String descricao) {
//		if(descricao == null) {
//			return null;
//		}
//		
//		return Arrays.stream(TipoDeConta.values())
//				.filter(tipo -> tipo.getDescricao().equals(descricao))
//				.findFirst()
//				.orElse(null);	
//		
//	}
//
//}
