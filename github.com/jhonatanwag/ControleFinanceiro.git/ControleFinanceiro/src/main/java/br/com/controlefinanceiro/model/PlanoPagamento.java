package br.com.controlefinanceiro.model;

import java.io.Serializable;

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

import br.com.controlefinanceiro.enums.FormaPagamento;
import br.com.controlefinanceiro.enums.TipoPagamento;

/**
 * 
 * @author Jhonatan
 */
@Entity
@Table(name = "plano_pagamento")
public class PlanoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_pl_pagamento_id")
	@SequenceGenerator(name = "gener_pl_pagamento_id", initialValue = 1, sequenceName = "gener_pl_pagamento_id", allocationSize = 1)
	@Column(name = "gener_pl_pagamento_id")
	private Long id;
	@Column(name = "descricao", nullable = false)
	private String descricao;
	@Column(name = "max_parcela", nullable = false)
	private Integer max_parcela;	
	@Column(name = "tipo_pagamento", nullable = false)
	private TipoPagamento tipoPagamento;
	@Column(name = "forma_pagamento", nullable = false)
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;	
	@Column(name = "status")
	private Boolean status;
	@Column(name = "venda")
	private Boolean venda;
	@Column(name = "compra")
	private Boolean compra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getMax_parcela() {
		return max_parcela;
	}

	public void setMax_parcela(Integer max_parcela) {
		this.max_parcela = max_parcela;
	}
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}	

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}	

	public Boolean getVenda() {
		return venda;
	}

	public void setVenda(Boolean venda) {
		this.venda = venda;
	}

	public Boolean getCompra() {
		return compra;
	}

	public void setCompra(Boolean compra) {
		this.compra = compra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		PlanoPagamento other = (PlanoPagamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
