package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Cor;
import br.com.controlefinanceiro.service.CadastroCorService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCorBean extends BeanAbstrato<Cor>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCorService cadastroCorService;

	@Override
	public void inicializar() {
			if(this.getObjeto() == null){
				limpar();
			}
		
	}

	@Override
	protected void limpar() {
		setObjeto(new Cor());
	}

	@Override
	public void salvar() {
		setObjeto(cadastroCorService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Cor salvo com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

}