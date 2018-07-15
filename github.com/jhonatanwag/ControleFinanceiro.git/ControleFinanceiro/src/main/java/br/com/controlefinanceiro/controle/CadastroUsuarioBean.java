package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.repository.Pessoas;
import br.com.controlefinanceiro.service.CadastroUsuarioService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean extends BeanAbstrato<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoaSelecionada;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	@Inject
	private Pessoas pessoas;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Usuario());
	}

	@Override
	public void salvar() {
		boolean padrao = true;
		// O primeiro item do target é considerado como padrão!		
		cadastroUsuarioService.salvar(getObjeto());
		limpar();
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	public void pessoaPorId() {
		Pessoa pessoa = pessoas.porId((getPessoaSelecionada().getId() == null ? -1 : getPessoaSelecionada().getId()));
		if (pessoa != null) {
			setPessoaSelecionada(pessoa);
			getObjeto().setPessoa(pessoa);
		} else {
			setPessoaSelecionada(new Pessoa());
			getObjeto().setPessoa(null);
			pessoaSelecionada.setId(null);
			FacesUtil.addErrorMessage("Pessoa não encontrada para o código informado!");
		}
	}

	public void pessoaSelecao(SelectEvent event) {
		Pessoa pessoa = (Pessoa) event.getObject();
		this.pessoaSelecionada = pessoa;
		if (pessoa == null) {
			FacesUtil.addErrorMessage("Pessoa Selecionada não encontrada");
		} else {
			getObjeto().setPessoa(pessoa);
		}
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

}