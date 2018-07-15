package br.com.controlefinanceiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.controlefinanceiro.model.PlanoPagamento;
import br.com.controlefinanceiro.repository.PlanoPagamentos;

@FacesConverter(forClass = PlanoPagamento.class)
public class PlanoPagamentoConverter implements Converter {

	@Inject
	private PlanoPagamentos planoPagamentos;



	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PlanoPagamento retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = planoPagamentos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			PlanoPagamento tp = (PlanoPagamento) value;
			return tp.getId() == null ? null : tp.getId().toString();
		}

		return "";
	}

}
