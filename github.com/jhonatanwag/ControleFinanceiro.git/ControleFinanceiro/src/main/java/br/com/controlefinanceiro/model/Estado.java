/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "gener_estado_id", initialValue = 1, sequenceName = "gener_estado_id", allocationSize = 1)
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_estado_id")
	@Column(name = "estado_id")
	private Long id;
	@Column(name = "codigo_ibge", nullable = false)
	private Long codigoIbge;
	@Column(length = 60, nullable = false, unique = true)
	private String nome;
	@Column(length = 2, nullable = false)
	private String uf;
	@ManyToOne(targetEntity = Pais.class, optional = false)
	@JoinColumn(name = "pais_id")
	private Pais pais;
	@OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
	private List<Cidade> cidades = new ArrayList<Cidade>();
	@Version
	private Long versao;

	public Estado() {
	}

	public Estado(Long codigoIbge, String nome, String uf, Pais pais) {
		this.codigoIbge = codigoIbge;
		this.nome = nome;
		this.uf = uf;
		this.pais = pais;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Long getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Long codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Estado other = (Estado) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
			return false;
		}
		if ((this.uf == null) ? (other.uf != null) : !this.uf.equals(other.uf)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
		hash = 73 * hash + (this.nome != null ? this.nome.hashCode() : 0);
		hash = 73 * hash + (this.uf != null ? this.uf.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}

	public String estadoPais() {
		return "(" + this.uf + ") " + this.nome + " - " + this.pais.getNome();
	}

}
