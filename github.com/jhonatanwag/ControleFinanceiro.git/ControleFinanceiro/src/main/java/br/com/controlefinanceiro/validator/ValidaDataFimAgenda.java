package br.com.controlefinanceiro.validator;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.controlefinanceiro.util.DataUtils;


@FacesValidator(value = "validaDataFim")
public class ValidaDataFimAgenda implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		Date dataInicio = (Date) component.getAttributes().get(
				"paramDataInicio");
		if (value != null) {
			Date dataFim = (Date) value;
			if (dataFim.before(dataInicio)) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro",
						"Data fim  deve ser igual a data início!"));
			}

			if(DataUtils.validaDataMaior(dataInicio, dataFim)){
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro",
						"Data fim não pode ser maior que a data início!"));
			}
					
		}
	}
}	