/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.controlefinanceiro.enums.TipoEmail;

/**
 * 
 *
 */
@Entity
@Table(name = "email", uniqueConstraints = {
		@UniqueConstraint(name = "uk_pessoa_email", columnNames = { "pessoa_id", "endereco" }) })
public class Email implements Serializable, Comparable<Email> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_email_id")
	@SequenceGenerator(name = "gener_email_id", initialValue = 1, sequenceName = "gener_email_id", allocationSize = 1)
	@Column(name = "email_id")
	private Long id;
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@Column(length = 80, nullable = false)
	private String endereco;
	@Column(length = 3)
	private Integer sequencia;
	@Enumerated(EnumType.STRING)
	private TipoEmail tipoEmail;
	private Boolean padrao = Boolean.FALSE;
	@Version
	private Long versao;

	public Email() {
	}

	public Email(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Email(Pessoa pessoa, String endereco, TipoEmail tipoEmail) {
		this.pessoa = pessoa;
		this.endereco = endereco;
		this.tipoEmail = tipoEmail;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
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

	@Override
	public int compareTo(Email o) {
		return this.getSequencia().compareTo(o.getSequencia());
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
		Email other = (Email) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
