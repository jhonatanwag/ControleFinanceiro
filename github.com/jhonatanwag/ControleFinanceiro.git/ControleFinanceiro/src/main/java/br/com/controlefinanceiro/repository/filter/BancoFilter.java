package br.com.controlefinanceiro.repository.filter;

import java.io.Serializable;

public class BancoFilter implements Serializable, Filter {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private String nomeBanco;
	private String codigoCompensacao;

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

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getCodigoCompensacao() {
		return codigoCompensacao;
	}

	public void setCodigoCompensacao(String codigoCompensacao) {
		this.codigoCompensacao = codigoCompensacao;
	}

}