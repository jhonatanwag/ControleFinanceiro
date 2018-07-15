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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.controlefinanceiro.enums.Categoria;
import br.com.controlefinanceiro.enums.SituacaoCadastro;

/**
 * 
 * @author Jhonatan
 */
@Entity
@Table(name = "grupo")
@SequenceGenerator(name = "gener_grupo_id", initialValue = 1, sequenceName = "gener_grupo_id", allocationSize = 1)
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_grupo_id")
	@Column(name = "grupo_id")
	private Long id;
	@Column(length = 60, unique = true, nullable = false)
	private String descricao;
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "grupo_pai", insertable = false, updatable = false)
	private Grupo grupoPai;
	@Fetch(FetchMode.JOIN)
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_cadastro", insertable = false, updatable = false)
	private Usuario usuarioCadastro;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_cadastro")
	private SituacaoCadastro situacaoCadastro = SituacaoCadastro.ATIVO;
	@Enumerated(EnumType.STRING)
	@Column(name = "categoria")
	private Categoria categoria = Categoria.GERAIS;
	@Column(name = "deduzir_irpf")
	private Boolean deduzirIRPF;
	@Version
	private Long versao;
	@Transient
	private Boolean jaNaArvore = false;

	public Grupo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacaoCadastro getSituacaoCadastro() {
		return situacaoCadastro;
	}

	public void setSituacaoCadastro(SituacaoCadastro situacaoCadastro) {
		this.situacaoCadastro = situacaoCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public Grupo getGrupoPai() {
		return grupoPai;
	}

	public void setGrupoPai(Grupo grupoPai) {
		this.grupoPai = grupoPai;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Boolean getDeduzirIRPF() {
		return deduzirIRPF;
	}

	public void setDeduzirIRPF(Boolean deduzirIRPF) {
		this.deduzirIRPF = deduzirIRPF;
	}

	public Boolean getJaNaArvore() {
		return jaNaArvore;
	}

	public void setJaNaArvore(Boolean jaNaArvore) {
		this.jaNaArvore = jaNaArvore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Grupo other = (Grupo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+ id + " - " + descricao;
	}

}
