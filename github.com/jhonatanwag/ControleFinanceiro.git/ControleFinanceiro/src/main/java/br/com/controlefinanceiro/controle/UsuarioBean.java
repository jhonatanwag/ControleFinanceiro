package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.repository.Usuarios;
import br.com.controlefinanceiro.security.UsuarioLogado;
import br.com.controlefinanceiro.security.UsuarioSistema;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuario;

	public void alterarSenha() {
		usuarios.alterarSenha(usuario.getUsuario());
		FacesUtil.addInfoMessage("Senha alterada com sucesso!");
	}

	public UsuarioSistema getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistema usuario) {
		this.usuario = usuario;
	}
	
	

}