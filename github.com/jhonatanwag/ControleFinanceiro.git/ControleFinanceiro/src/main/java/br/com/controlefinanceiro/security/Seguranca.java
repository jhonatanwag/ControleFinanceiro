package br.com.controlefinanceiro.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.repository.Pessoas;

@Named
@RequestScoped
public class Seguranca {

	@Inject
	private ExternalContext externalContext;
	@Inject
	private Pessoas pessoas;

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getLogin();
		}

		return nome;
	}

	public String getEnderecoIp() {
		String enderecoIp = getEnderecoIpLocal();
		return enderecoIp;
	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}
	
	@Produces
	@PessoaLogado
	public Pessoa getPessoaLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario.getUsuario().getPessoa();
	}

	/**
	 * 
	 * @return o endereço do computador local
	 */
	@Produces
	@EnderecoIpLocal
	public String getEnderecoIpLocal() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		String ipAddress = request.getRemoteAddr();
		System.out.println("ipAddress:" + ipAddress);
		return ipAddress;

	}

	public boolean isEmitirPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
	}

	public boolean isCancelarPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
	}

}
