package br.com.controlefinanceiro.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class CorFilter implements Serializable, Filter {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}