package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.controlefinanceiro.enums.SituacaoCadastro;
import br.com.controlefinanceiro.enums.SituacaoParcela;

/**
 *
 * @author Jhonatan
 */
@Entity
@Table(name = "parcela")
public class Parcela implements Serializable, Comparable<Parcela> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_parcela_id")
	@SequenceGenerator(name = "gener_parcela_id", initialValue = 1, sequenceName = "gener_parcela_id", allocationSize = 1)
	@Column(name = "parcela_id")
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "programacao_id")
	private Programacao programacao;
	@Column(name = "num_parcela", nullable = false)
	private Integer numeroParcela;
	@Column(name = "valor", nullable = false)
	private BigDecimal valor = BigDecimal.ZERO;
	@Column(name = "valor_pago", nullable = false)
	private BigDecimal valorPago = BigDecimal.ZERO;
	@Column(name = "data_vecimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@Column(name = "data_pagamento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_parcela")
	private SituacaoParcela situacaoParcela = SituacaoParcela.AVENCER;

	@Transient
	private Boolean selecionado = Boolean.FALSE;

	public Parcela() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Programacao getProgramacao() {
		return programacao;
	}

	public void setProgramacao(Programacao programacao) {
		this.programacao = programacao;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public SituacaoParcela getSituacaoParcela() {
		return situacaoParcela;
	}

	public void setSituacaoParcela(SituacaoParcela situacaoParcela) {
		this.situacaoParcela = situacaoParcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parcela other = (Parcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Parcela o) {
		return this.getNumeroParcela().compareTo(o.getNumeroParcela());
	}

}
