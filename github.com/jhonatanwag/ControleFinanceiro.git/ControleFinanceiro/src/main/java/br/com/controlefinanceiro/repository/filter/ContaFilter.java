package br.com.controlefinanceiro.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class ContaFilter implements Serializable, Filter {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private Long codigoIbgeDe;
	private Long codigoIbgeAte;
	private String descricao;
	private Date dataCadastroInicio;
	private Date dataCadastroFim;

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

	public Long getCodigoIbgeDe() {
		return codigoIbgeDe;
	}

	public void setCodigoIbgeDe(Long codigoIbgeDe) {
		this.codigoIbgeDe = codigoIbgeDe;
	}

	public Long getCodigoIbgeAte() {
		return codigoIbgeAte;
	}

	public void setCodigoIbgeAte(Long codigoIbgeAte) {
		this.codigoIbgeAte = codigoIbgeAte;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastroInicio() {
		return dataCadastroInicio;
	}

	public void setDataCadastroInicio(Date dataCadastroInicio) {
		this.dataCadastroInicio = dataCadastroInicio;
	}

	public Date getDataCadastroFim() {
		return dataCadastroFim;
	}

	public void setDataCadastroFim(Date dataCadastroFim) {
		this.dataCadastroFim = dataCadastroFim;
	}

}