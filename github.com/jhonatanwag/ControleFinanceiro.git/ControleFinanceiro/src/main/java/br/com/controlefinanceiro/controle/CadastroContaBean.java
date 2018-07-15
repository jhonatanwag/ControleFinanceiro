package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.enums.SituacaoCadastro;
import br.com.controlefinanceiro.enums.TipoConta;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.security.PessoaLogado;
import br.com.controlefinanceiro.service.CadastroContaService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroContaBean extends BeanAbstrato<Conta> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroContaService cadastroContaService;
	@Inject
	@PessoaLogado
	private Pessoa pessoa;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
		getObjeto().setDataCadastro(new Date());
	}

	@Override
	public void setObjeto(Conta objeto) {
		super.setObjeto(objeto);
		if (getObjeto() != null) {

		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Conta());
	}

	@Override
	public void salvar() {
		getObjeto().setPessoa(pessoa);
		setObjeto(cadastroContaService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Conta salva com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	public TipoConta[] getTipoContas() {
		return TipoConta.values();
	}
	
	public SituacaoCadastro[] getSituacaoCadastros() {
		return SituacaoCadastro.values();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}