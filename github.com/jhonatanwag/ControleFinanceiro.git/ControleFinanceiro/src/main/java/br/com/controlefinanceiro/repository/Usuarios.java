package br.com.controlefinanceiro.repository;

import java.io.Serializable;

import javax.persistence.NoResultException;

import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class Usuarios extends RepositoryAbstrato<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Usuarios() {
		super(Usuario.class);
	}

	@Override
	public Usuario salvar(Usuario objeto) {
		return super.salvar(objeto);
	}

	public Usuario porLogin(String login) {
		Usuario usuario = null;
		try {
			usuario = manager.createQuery("from Usuario where upper(login) = :login", Usuario.class)
					.setParameter("login", login.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			// Nenhum usuário encontrado para o login informado!
		}
		return usuario;
	}

	@Transactional
	public Usuario alterarSenha(Usuario usuario) {
		// Usuario usu = usuario;
		// usu.setSenha(usuario.getNovaSenha().toLowerCase());
		return manager.merge(usuario);
	}

}
