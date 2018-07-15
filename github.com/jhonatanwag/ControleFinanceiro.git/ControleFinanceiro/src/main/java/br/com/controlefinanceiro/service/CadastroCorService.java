package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Cor;
import br.com.controlefinanceiro.repository.Cores;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroCorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cores cores;

	@Transactional
	public Cor salvar(Cor cor) {
		Cor corExiste = cores.porDescricaoUnico(cor.getDescricao(), cor.getId());

		if (corExiste != null && !corExiste.equals(cor)) {
			throw new NegocioException("Já existe uma cor com a mesma descrição cadastrada.");
		}

		return cores.salvar(cor);
	}

}
