package br.com.controlefinanceiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.repository.Contas;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter {

	@Inject
	private Contas paises;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Conta retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = paises.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Conta conta = (Conta) value;
			return conta.getId() == null ? null : conta.getId().toString();
		}
		
		return "";
	}

}
