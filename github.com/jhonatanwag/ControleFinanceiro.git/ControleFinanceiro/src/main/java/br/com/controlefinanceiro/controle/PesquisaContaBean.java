package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.repository.Contas;
import br.com.controlefinanceiro.repository.filter.ContaFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaContaBean extends PesquisaBeanAbstrato<Conta, ContaFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Contas contas;

	public PesquisaContaBean() {
		setFiltro(new ContaFilter());
	}

	@Override
	public void excluir() {
		contas.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Conta " + getObjetoSelecionado().getDescricao() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(contas.filtrados(getFiltro()));
	}

}