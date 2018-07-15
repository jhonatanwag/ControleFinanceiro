package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.controlefinanceiro.enums.TipoMovimentacao;
import br.com.controlefinanceiro.enums.TipoPagamento;

@Entity
@Table(name = "programacao")
public class Programacao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_programacao_id")
	@SequenceGenerator(name = "gener_programacao_id", initialValue = 1, sequenceName = "gener_programacao_id", allocationSize = 1)
	@Column(name = "programacao_id")
	private Long id;
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_pgto")
	private Date dataPagamento;
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro = new Date();
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	@Column(name = "descricao", nullable = false, length = 30)
	private String descricao;
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "programacao", fetch = FetchType.LAZY)
	private List<Parcela> parcelas = new ArrayList<Parcela>();
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	@ManyToOne(targetEntity = Conta.class, optional = false)
	@JoinColumn(name = "conta_id")
	private Conta conta;
	@ManyToOne(targetEntity = Conta.class, optional = true)
	@JoinColumn(name = "conta_transferencia_id")
	private Conta contaTransferencia;
	@ManyToOne(targetEntity = Pessoa.class, optional = false)
	@JoinColumn(name = "local_id")
	private Pessoa local;
	@ManyToOne(targetEntity = Grupo.class, optional = false)
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;
	
	@Column(name="planejado")
	private Boolean planejado;
	
	@Column(name = "irpf_cpf", nullable = true, length = 11)
	private String irpfCPF;
	@Column(name = "irpf_obs", nullable = true, length = 300)
	private String irpfObs;
	@Column(name = "irpf_anexo", nullable = true)
	private Byte[] ifprAnexo;	
	
	@Column(name="qtd_parcela")
	private BigDecimal qtdParcela;
	@Column(name="data_parcela")
	private BigDecimal dataParcela;
	@Column(name="valor_entrada")
	private BigDecimal valorEntrada;

	public Programacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getContaTransferencia() {
		return contaTransferencia;
	}

	public void setContaTransferencia(Conta contaTransferencia) {
		this.contaTransferencia = contaTransferencia;
	}	

	public Pessoa getLocal() {
		return local;
	}

	public void setLocal(Pessoa local) {
		this.local = local;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}	

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Boolean getPlanejado() {
		return planejado;
	}

	public void setPlanejado(Boolean planejado) {
		this.planejado = planejado;
	}

	public String getIrpfCPF() {
		return irpfCPF;
	}

	public void setIrpfCPF(String irpfCPF) {
		this.irpfCPF = irpfCPF;
	}

	public String getIrpfObs() {
		return irpfObs;
	}

	public void setIrpfObs(String irpfObs) {
		this.irpfObs = irpfObs;
	}

	public Byte[] getIfprAnexo() {
		return ifprAnexo;
	}

	public void setIfprAnexo(Byte[] ifprAnexo) {
		this.ifprAnexo = ifprAnexo;
	}

	public BigDecimal getQtdParcela() {
		return qtdParcela;
	}

	public void setQtdParcela(BigDecimal qtdParcela) {
		this.qtdParcela = qtdParcela;
	}

	public BigDecimal getDataParcela() {
		return dataParcela;
	}

	public void setDataParcela(BigDecimal dataParcela) {
		this.dataParcela = dataParcela;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
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
		Programacao other = (Programacao) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
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

	@Override
	public String toString() {
		return "Programacao [id=" + id + ", dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento
				+ ", dataCadastro=" + dataCadastro + ", descricao=" + descricao + ", conta=" + conta + "]";
	}

}