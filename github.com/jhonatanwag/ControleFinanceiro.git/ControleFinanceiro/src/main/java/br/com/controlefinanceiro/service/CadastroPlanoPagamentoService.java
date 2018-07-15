package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.PlanoPagamento;
import br.com.controlefinanceiro.repository.PlanoPagamentos;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroPlanoPagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlanoPagamentos planoPagamentos;

	@Transactional
	public PlanoPagamento salvar(PlanoPagamento planoPagamento) {
		PlanoPagamento planoPagamentoExiste = planoPagamentos.porDescricaoUnico(planoPagamento.getDescricao());

		if (planoPagamentoExiste != null && !planoPagamentoExiste.equals(planoPagamento)) {
			throw new NegocioException("Já existe um plano de pagamento com o mesma descrição cadastrado.");
		}

		return planoPagamentos.salvar(planoPagamento);
	}

}
