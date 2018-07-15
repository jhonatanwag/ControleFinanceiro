package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.repository.Pessoas;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExiste = pessoas.porLoginUsuarioUnico(usuario.getLogin(), usuario.getId());

		if (usuarioExiste != null && !usuarioExiste.equals(usuario)) {
			throw new NegocioException("Já existe um usuário com o mesmo login cadastrado.");
		}

		return pessoas.salvar(usuario);
	}

}
