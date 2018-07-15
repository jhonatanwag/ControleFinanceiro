package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.enums.TipoPagamento;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.PlanoPagamento;
import br.com.controlefinanceiro.repository.Contas;
import br.com.controlefinanceiro.repository.filter.ContaFilter;
import br.com.controlefinanceiro.service.CadastroPlanoPagamentoService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPlanoPagamentoBean extends BeanAbstrato<PlanoPagamento> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPlanoPagamentoService cadastroPlanoPagamentoService;
	@Inject
	private Contas contas;
	private List<Conta> todasContas;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
		todasContas = contas.filtrados(new ContaFilter());
	}

	@Override
	protected void limpar() {
		setObjeto(new PlanoPagamento());
	}

	@Override
	public void salvar() {
		setObjeto(cadastroPlanoPagamentoService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Plano de Pagamento salvo com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	public TipoPagamento[] getTipoPagamentos() {
		return TipoPagamento.values();
	}

	public Contas getContas() {
		return contas;
	}

	public void setContas(Contas contas) {
		this.contas = contas;
	}

	public List<Conta> getTodasContas() {
		return todasContas;
	}

	public void setTodasContas(List<Conta> todasContas) {
		this.todasContas = todasContas;
	}

}