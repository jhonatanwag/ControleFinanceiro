package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Cidade;
import br.com.controlefinanceiro.repository.Cidades;
import br.com.controlefinanceiro.repository.filter.CidadeFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCidadeBean extends PesquisaBeanAbstrato<Cidade, CidadeFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cidades cidades;

	public PesquisaCidadeBean() {
		setFiltro(new CidadeFilter());
	}

	@Override
	public void excluir() {
		cidades.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Cidade " + getObjetoSelecionado().getNome() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(cidades.filtrados(getFiltro()));
	}

}