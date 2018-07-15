package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Pais;
import br.com.controlefinanceiro.repository.Paises;
import br.com.controlefinanceiro.repository.filter.PaisFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPaisBean extends PesquisaBeanAbstrato<Pais, PaisFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Paises paises;
	
	public PesquisaPaisBean() {
		setFiltro(new PaisFilter());
	}

	@Override
	public void excluir() {
		paises.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Pais " + getObjetoSelecionado().getNome() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(paises.filtrados(getFiltro()));
	}

}