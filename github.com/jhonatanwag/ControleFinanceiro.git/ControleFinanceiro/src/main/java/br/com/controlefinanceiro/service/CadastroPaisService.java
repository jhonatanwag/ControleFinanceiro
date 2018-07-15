package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Pais;
import br.com.controlefinanceiro.repository.Paises;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroPaisService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Paises paises;

	@Transactional
	public Pais salvar(Pais pais) {
		Pais paisExiste = paises.porNomeUnico(pais.getNome());

		if (paisExiste != null && !paisExiste.equals(pais)) {
			throw new NegocioException("Já existe um país com o mesmo nome cadastrado.");
		}

		return paises.salvar(pais);
	}

}
