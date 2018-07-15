package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Pais;
import br.com.controlefinanceiro.service.CadastroPaisService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPaisBean extends BeanAbstrato<Pais> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPaisService cadastroPaisService;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Pais());
	}

	@Override
	public void salvar() {
		setObjeto(cadastroPaisService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Pais salvo com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

}