package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Marca;
import br.com.controlefinanceiro.service.CadastroMarcaService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMarcaBean extends BeanAbstrato<Marca>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroMarcaService cadastroMarcaService;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Marca());
	}

	@Override
	public void salvar() {
		setObjeto(cadastroMarcaService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Marca salvo com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

}