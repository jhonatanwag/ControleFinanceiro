package br.com.controlefinanceiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.controlefinanceiro.model.Pais;
import br.com.controlefinanceiro.repository.Paises;

@FacesConverter(forClass = Pais.class)
public class PaisConverter implements Converter {

	@Inject
	private Paises paises;
	
//	public PaisConverter() {
//		paises = CDIServiceLocator.getBean(Paises.class);
//	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pais retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = paises.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pais produto = (Pais) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		
		return "";
	}

}
