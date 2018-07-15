package br.com.controlefinanceiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.controlefinanceiro.model.Programacao;
import br.com.controlefinanceiro.repository.Programacaos;

@FacesConverter(forClass = Programacao.class)
public class ProgramacaoConverter implements Converter {

	@Inject
	private Programacaos programacaos;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Programacao retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = programacaos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Programacao Programacao = (Programacao) value;
			return Programacao.getId() == null ? null : Programacao.getId().toString();
		}
		
		return "";
	}

}
