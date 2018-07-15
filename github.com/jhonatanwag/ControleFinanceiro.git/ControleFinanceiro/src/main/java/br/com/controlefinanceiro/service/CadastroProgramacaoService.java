package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Programacao;
import br.com.controlefinanceiro.repository.Programacaos;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroProgramacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Programacaos programacaos;

	@Transactional
	public Programacao salvar(Programacao programacao) {
		Programacao movimentacaoExiste = programacaos.porDescricaoUnico(programacao.getDescricao(), programacao.getId());

		if (movimentacaoExiste != null && !movimentacaoExiste.equals(programacao)) {
			throw new NegocioException("Já existe uma programação com o mesmo nome: "+programacao.getDescricao());
		}		
				
		return programacaos.salvar(programacao);
	}

}
