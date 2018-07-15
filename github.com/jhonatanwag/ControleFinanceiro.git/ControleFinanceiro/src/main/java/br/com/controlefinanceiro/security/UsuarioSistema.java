package br.com.controlefinanceiro.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.controlefinanceiro.model.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	/**
	 * 
	 * 
	 *
	 *O segredo do spring security esta aqui.(extends User)
	 *no super estou passando o usuario e a senha para comparação.
	 *por padrão a classe de criptografia do spring (DigestUtils.md5DigestAsHex) é lowercase a minha classe que salva no Oracle  é upper.
	 *
	 */
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getLogin(), usuario.getSenha().toLowerCase(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
