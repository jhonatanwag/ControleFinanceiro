package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Grupo;
import br.com.controlefinanceiro.repository.Grupos;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroGrupoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Grupos grupos;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		Grupo grupoExiste = grupos.porDescricaoUnico(grupo.getDescricao(), grupo.getId());

		if (grupoExiste != null && !grupoExiste.equals(grupo)) {
			throw new NegocioException("Já existe uma grupo com a mesma descrição cadastrada.");
		}

		return grupos.salvar(grupo);
	}

}
