package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Grupo;
import br.com.controlefinanceiro.repository.Grupos;
import br.com.controlefinanceiro.repository.filter.GrupoFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaGrupoBean extends PesquisaBeanAbstrato<Grupo, GrupoFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Grupos grupos;

	public PesquisaGrupoBean() {
		setFiltro(new GrupoFilter());
	}

	@Override
	public void excluir() {
		grupos.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Grupo " + getObjetoSelecionado().getDescricao() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(grupos.filtrados(getFiltro()));
	}

}