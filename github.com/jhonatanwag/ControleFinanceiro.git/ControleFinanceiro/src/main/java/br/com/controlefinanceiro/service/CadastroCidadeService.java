package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Cidade;
import br.com.controlefinanceiro.repository.Cidades;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroCidadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cidades cidades;

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Cidade cidadeExiste = cidades.porNomeEstadoUnico(cidade.getNome(), cidade.getEstado(), cidade.getId());

		if (cidadeExiste != null && !cidadeExiste.equals(cidade)) {
			throw new NegocioException("Já existe uma cidade com o mesmo nome cadastrado para o estado:"+cidade.getEstado().estadoPais());
		}		
				
		return cidades.salvar(cidade);
	}

}
