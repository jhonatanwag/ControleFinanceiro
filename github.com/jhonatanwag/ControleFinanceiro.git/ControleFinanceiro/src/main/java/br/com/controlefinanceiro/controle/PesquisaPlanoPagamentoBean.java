package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.PlanoPagamento;
import br.com.controlefinanceiro.repository.PlanoPagamentos;
import br.com.controlefinanceiro.repository.filter.PlanoPagamentoFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPlanoPagamentoBean extends PesquisaBeanAbstrato<PlanoPagamento, PlanoPagamentoFilter>
		implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlanoPagamentos planoPagamentos;

	public PesquisaPlanoPagamentoBean() {
		setFiltro(new PlanoPagamentoFilter());
	}

	@Override
	public void excluir() {
		planoPagamentos.remover(getObjetoSelecionado().getId());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("PlanoPagamento " + getObjetoSelecionado().getDescricao() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(planoPagamentos.filtrados(getFiltro()));
	}

}