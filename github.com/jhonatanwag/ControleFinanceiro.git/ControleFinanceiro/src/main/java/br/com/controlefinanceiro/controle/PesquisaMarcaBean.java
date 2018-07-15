package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Marca;
import br.com.controlefinanceiro.repository.Marcas;
import br.com.controlefinanceiro.repository.filter.MarcaFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMarcaBean extends PesquisaBeanAbstrato<Marca, MarcaFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Marcas marcas;

	public PesquisaMarcaBean() {
		setFiltro(new MarcaFilter());
	}

	@Override
	public void excluir() {
		marcas.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Marca " + getObjetoSelecionado().getDescricao() + " excluída com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(marcas.filtrados(getFiltro()));
	}

}