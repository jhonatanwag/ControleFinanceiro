package br.com.controlefinanceiro.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class PaisFilter implements Serializable, Filter {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private String nome;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}