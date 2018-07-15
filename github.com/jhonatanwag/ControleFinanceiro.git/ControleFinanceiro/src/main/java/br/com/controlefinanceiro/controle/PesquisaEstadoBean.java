package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Estado;
import br.com.controlefinanceiro.repository.Estados;
import br.com.controlefinanceiro.repository.filter.EstadoFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEstadoBean extends PesquisaBeanAbstrato<Estado, EstadoFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Estados estados;

	public PesquisaEstadoBean() {
		setFiltro(new EstadoFilter());
	}

	@Override
	public void excluir() {
		estados.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Estado " + getObjetoSelecionado().getNome() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(estados.filtrados(getFiltro()));
	}

}