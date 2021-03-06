package br.com.controlefinanceiro.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class GrupoFilter implements Serializable, Filter {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private String descricao;
	private Date dataCadastroInicio;
	private Date dataCadastroFim;
	private Boolean vendaGourmet;

	public GrupoFilter() {
		super();
	}

	public GrupoFilter(Boolean vendaGourmet) {
		super();
		this.vendaGourmet = vendaGourmet;
	}

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

	public Boolean getVendaGourmet() {
		return vendaGourmet;
	}

	public void setVendaGourmet(Boolean vendaGourmet) {
		this.vendaGourmet = vendaGourmet;
	}

}