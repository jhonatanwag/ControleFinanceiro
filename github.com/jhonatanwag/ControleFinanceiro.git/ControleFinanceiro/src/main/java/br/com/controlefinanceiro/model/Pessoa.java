/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.controlefinanceiro.enums.SituacaoCadastro;
import br.com.controlefinanceiro.enums.TipoPessoa;

/**
 * 
 * @author Jhonatan
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "pessoa_id")
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_cadastro", nullable = false)
	private SituacaoCadastro situacaoCadastro = SituacaoCadastro.ATIVO;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa", nullable = false)
	private TipoPessoa tipoPessoa;
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_cadastro", insertable = false, updatable = false)
	private Usuario usuarioCadastro;
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(name = "razao_social")
	private String razaoSocial;
	@Column(name = "documento_federal", length = 20)
	private String documentoFederal;
	@Column(name = "documento_estadual", length = 20)
	private String documentoEstadual;
	@Column(name = "observacao")
	private String observacao;
	@OneToMany(cascade = {
			javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pessoa", orphanRemoval = true)
	private List<Telefone> telefones = new ArrayList<>();
	@OneToMany(cascade = {
			javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pessoa", orphanRemoval = true)
	private List<Email> emails = new ArrayList<>();
	@OneToMany(cascade = {
			javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pessoa", orphanRemoval = true)
	private List<Enderecos> enderecos = new ArrayList<>();
	@OneToMany(cascade = {
			javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pessoa", orphanRemoval = true)
	private List<Conta> contas = new ArrayList<>();

	@OneToOne(cascade = {
			javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pessoa", orphanRemoval = true)
	private Usuario usuario;
	private boolean cliente = true;
	private boolean fornecedor = false;
	private boolean funcionario = false;

	@Version
	private Long versao;

	public boolean isCliente() {
		return cliente;
	}

	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}

	public boolean isFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isFuncionario() {
		return funcionario;
	}

	public void setFuncionario(boolean funcionario) {
		this.funcionario = funcionario;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Enderecos> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Enderecos> enderecos) {
		this.enderecos = enderecos;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public SituacaoCadastro getSituacaoCadastro() {
		return situacaoCadastro;
	}

	public void setSituacaoCadastro(SituacaoCadastro situacaoCadastro) {
		this.situacaoCadastro = situacaoCadastro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDocumentoEstadual() {
		return documentoEstadual;
	}

	public void setDocumentoEstadual(String documentoEstadual) {
		this.documentoEstadual = documentoEstadual;
	}

	public String getDocumentoFederal() {
		return documentoFederal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDocumentoFederal(String documentoFederal) {
		if (!documentoFederal.equals("   .   .   -  ") && this.tipoPessoa.equals(TipoPessoa.FISICA)) {
			this.documentoFederal = documentoFederal;
		} else if (!documentoFederal.equals("  .   .   /    -  ") && this.tipoPessoa.equals(TipoPessoa.JURIDICA)) {
			this.documentoFederal = documentoFederal;
		} else {
			this.documentoFederal = null;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Pessoa other = (Pessoa) obj;
		if (this.tipoPessoa != other.tipoPessoa) {
			return false;
		}
		if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
			return false;
		}
		if ((this.documentoFederal == null) ? (other.documentoFederal != null)
				: !this.documentoFederal.equals(other.documentoFederal)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.tipoPessoa != null ? this.tipoPessoa.hashCode() : 0);
		hash = 17 * hash + (this.nome != null ? this.nome.hashCode() : 0);
		hash = 17 * hash + (this.documentoFederal != null ? this.documentoFederal.hashCode() : 0);
		return hash;
	}
}
