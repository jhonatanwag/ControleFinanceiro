package br.com.controlefinanceiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.controlefinanceiro.model.Cor;
import br.com.controlefinanceiro.repository.Cores;

@FacesConverter(forClass = Cor.class)
public class CorConverter implements Converter {

	@Inject
	private Cores cores;

	// public CorConverter() {
	// cores = CDIServiceLocator.getBean(Cores.class);
	// }

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cor retorno = null;
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = cores.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cor marca = (Cor) value;
			return marca.getId() == null ? null : marca.getId().toString();
		}

		return "";
	}

}
