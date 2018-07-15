package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Cor;
import br.com.controlefinanceiro.repository.Cores;
import br.com.controlefinanceiro.repository.filter.CorFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCorBean extends PesquisaBeanAbstrato<Cor, CorFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cores marcas;

	public PesquisaCorBean() {
		setFiltro(new CorFilter());
	}

	@Override
	public void excluir() {
		marcas.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Cor " + getObjetoSelecionado().getDescricao() + " excluída com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(marcas.filtrados(getFiltro()));
	}

}