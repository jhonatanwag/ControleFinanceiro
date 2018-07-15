package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Programacao;
import br.com.controlefinanceiro.repository.Programacaos;
import br.com.controlefinanceiro.repository.filter.ProgramacaoFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProgramacaoBean extends PesquisaBeanAbstrato<Programacao, ProgramacaoFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Programacaos Programacaos;

	public PesquisaProgramacaoBean() {
		setFiltro(new ProgramacaoFilter());
	}

	@Override
	public void excluir() {
		Programacaos.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Programacao " + getObjetoSelecionado().getDescricao() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(Programacaos.filtrados(getFiltro()));
	}

}