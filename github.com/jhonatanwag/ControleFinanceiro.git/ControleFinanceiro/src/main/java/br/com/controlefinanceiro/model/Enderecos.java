/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.UniqueConstraint;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.controlefinanceiro.enums.TipoEndereco;

/**
 * 
 * 
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_endereco_seq", columnNames = { "pessoa_id", "seq" }) })
public class Enderecos implements Serializable, Comparable<Enderecos> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_ender_id")
	@SequenceGenerator(name = "gener_ender_id", initialValue = 1, sequenceName = "gener_ender_id", allocationSize = 1)
	@Column(name = "enderecos_id")
	private Long id;
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@Embedded
	private Endereco endereco = new Endereco();
	@Column(name = "seq", length = 3)
	private Integer sequencia;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_endereco")
	private TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
	private Boolean padrao = Boolean.FALSE;
	@Version
	private Long versao;

	public Enderecos() {
	}

	/**
	 * 
	 * @param endereco
	 *            Construtor Copy constructors
	 */
	public Enderecos(Enderecos endereco) {
		this.id = endereco.id;
		// this.pessoa = endereco.pessoa;
		this.endereco = endereco.endereco;
		this.sequencia = endereco.sequencia;
		this.tipoEndereco = endereco.tipoEndereco;
		this.padrao = endereco.padrao;
	}

	public Enderecos(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Boolean getPadrao() {
		return padrao;
	}

	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public int compareTo(Enderecos o) {
		return this.getSequencia().compareTo(o.getSequencia());
	}

}
