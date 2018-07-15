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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
//import org.hibernate.validator.constraints.NotEmpty;

import br.com.controlefinanceiro.enums.TipoTelefone;

/**
 * 
 * @author Jhonatan
 */
@Entity
@Table(name = "telefone", uniqueConstraints = {
		@UniqueConstraint(name = "uk_pessoa_numero", columnNames = { "pessoa_id", "numero" }) })
public class Telefone implements Serializable, Comparable<Telefone> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "telefone_id")
	private Long id;
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@Column(name = "seq", length = 3)
	private Integer sequencia;
	@Column(length = 20, nullable = false)
	private String numero = "";
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_telefone")
	private TipoTelefone tipoTelefone;
	private Boolean padrao = Boolean.FALSE;
	@Version
	private Long versao;

	public Telefone() {
	}

	public Telefone(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public Telefone(Pessoa pessoa, String numero, TipoTelefone tipoTelefone) {
		this.pessoa = pessoa;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero.replace(" ", "");
	}

	public void setNumero(String numero) {
		this.numero = (numero.equals("(  )    -    ") ? null : numero);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
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
	public int compareTo(Telefone o) {
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
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
