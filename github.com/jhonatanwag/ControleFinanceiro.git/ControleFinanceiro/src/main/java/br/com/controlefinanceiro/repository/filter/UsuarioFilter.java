package br.com.controlefinanceiro.repository.filter;

import java.io.Serializable;

public class UsuarioFilter implements Serializable, Filter {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private String login;

	public Long getIdDe() {
		return idDe;
	}

	public void setIdDe(Long idDe) {
		this.idDe = idDe;
	}

	public Long getIdAte() {
		return idAte;
	}

	public void setIdAte(Long idAte) {
		this.idAte = idAte;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}