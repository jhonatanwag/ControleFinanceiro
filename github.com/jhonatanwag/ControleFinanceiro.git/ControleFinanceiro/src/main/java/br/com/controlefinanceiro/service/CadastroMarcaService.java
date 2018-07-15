package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Marca;
import br.com.controlefinanceiro.repository.Marcas;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroMarcaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Marcas marcas;

	@Transactional
	public Marca salvar(Marca marca) {
		Marca marcaExiste = marcas.porDescricaoUnico(marca.getDescricao(), marca.getId());

		if (marcaExiste != null && !marcaExiste.equals(marca)) {
			throw new NegocioException("Já existe ua marca com o mesma descrição cadastrada.");
		}

		return marcas.salvar(marca);
	}

}
