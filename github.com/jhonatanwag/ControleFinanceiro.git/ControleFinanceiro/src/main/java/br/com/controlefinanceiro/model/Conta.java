package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.controlefinanceiro.enums.SituacaoCadastro;
import br.com.controlefinanceiro.enums.TipoConta;

@Entity
@Table(name = "conta")
@SequenceGenerator(name = "gener_conta_id", initialValue = 1, sequenceName = "gener_conta_id", allocationSize = 1)
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_conta_id")
	@Column(name = "conta_id")
	private Long id;
	@Column(name = "descricao", nullable = false, length = 30, unique = true)
	private String descricao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro = new Date();
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_alteracao", nullable = false)
	private Date dataAlteracao = new Date();	
	@Column(name = "num_conta", nullable = true, length = 30)
	private String numeroConta;
	@Column(name = "obs", nullable = true, length = 40)
	private String obs;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_cadastro", nullable = false)
	private SituacaoCadastro situacaoCadastro = SituacaoCadastro.ATIVO;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta", nullable = false)
	private TipoConta tipoConta = TipoConta.PADRAO;
	@Column(name = "saldo_inicial")
	private BigDecimal saldoInicial = BigDecimal.ZERO;
	@Column(name = "saldo_atual")
	private BigDecimal saldo_atual = BigDecimal.ZERO;
	@Version
	private Long versao;

	public Conta() {
	}

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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}	

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public SituacaoCadastro getSituacaoCadastro() {
		return situacaoCadastro;
	}

	public void setSituacaoCadastro(SituacaoCadastro situacaoCadastro) {
		this.situacaoCadastro = situacaoCadastro;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}	

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public BigDecimal getSaldo_atual() {
		return saldo_atual;
	}

	public void setSaldo_atual(BigDecimal saldo_atual) {
		this.saldo_atual = saldo_atual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

}