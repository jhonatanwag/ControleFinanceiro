package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.repository.Contas;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroContaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Contas contas;

	@Transactional
	public Conta salvar(Conta conta) {
		Conta contaExiste = contas.porDescricaoUnico(conta.getDescricao(), conta.getId());

		if (contaExiste != null && !contaExiste.equals(conta)) {
			throw new NegocioException("Já existe uma conta com o mesmo nome: "+conta.getDescricao());
		}		
				
		return contas.salvar(conta);
	}

}
