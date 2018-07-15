package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Estado;
import br.com.controlefinanceiro.repository.Estados;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroEstadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Estados estados;

	@Transactional
	public Estado salvar(Estado estado) {
		Estado estadoExiste = estados.porNomeUnico(estado.getNome(), estado.getId());

		if (estadoExiste != null && !estadoExiste.equals(estado)) {
			throw new NegocioException("Já existe um estado com o mesmo nome cadastrado.");
		}

		estadoExiste = estados.porCodigoIbge(estado.getCodigoIbge(), estado.getId());
		if (estadoExiste != null && !estadoExiste.equals(estado)) {
			throw new NegocioException("Já existe um estado com o mesmo código IBGE cadastrado.");
		}
		
		estadoExiste = estados.porUfUnico(estado.getUf(), estado.getId());
		if (estadoExiste != null && !estadoExiste.equals(estado)) {
			throw new NegocioException("Já existe um estado com o mesma Uf cadastrado.");
		}

		return estados.salvar(estado);
	}

}
