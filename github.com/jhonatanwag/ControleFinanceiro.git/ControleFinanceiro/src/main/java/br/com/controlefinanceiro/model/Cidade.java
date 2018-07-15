/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * 
 */
@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "gener_cidade_id", initialValue = 1, sequenceName = "gener_cidade_id", allocationSize = 1)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_cidade_id")
	@Column(name = "cidade_id")
	private Long id;
	@Column(name = "codigo_ibge", nullable = false, unique = true)
	private Long codigoIBGE;
	@Column(length = 60, nullable = false, unique = true)
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro = new Date();
	@ManyToOne(targetEntity = Estado.class, optional = false)
	@JoinColumn(name = "estado_id")
	private Estado estado;
	@Version
	private Long versao;

	public Cidade() {
	}

	public Cidade(Long codigoIBGE, String nome, Estado estado) {
		this.codigoIBGE = codigoIBGE;
		this.nome = nome;
		this.estado = estado;
	}

	public Long getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(Long codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public String getCidadeEstado() {
		return this.nome + "-" + this.estado.getUf();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Cidade other = (Cidade) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
			return false;
		}
		if (this.estado != other.estado && (this.estado == null || !this.estado.equals(other.estado))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
		hash = 23 * hash + (this.nome != null ? this.nome.hashCode() : 0);
		hash = 23 * hash + (this.estado != null ? this.estado.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
