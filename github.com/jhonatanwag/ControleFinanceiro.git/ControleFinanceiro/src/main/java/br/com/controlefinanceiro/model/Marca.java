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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import br.com.controlefinanceiro.enums.SituacaoCadastro;

/**
 * 
 * 
 */
@Entity
@Table(name = "marca")
@SequenceGenerator(name = "gener_marca_id", initialValue = 1, sequenceName = "gener_marca_id", allocationSize = 1)
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gener_marca_id")
	@Column(name = "marca_id")
	private Long id;
	@Column(length = 80, nullable = false, unique = true)
	private String descricao;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_cadastro", nullable = false)
	private SituacaoCadastro situacaoCadastro = SituacaoCadastro.ATIVO;
	@Version
	private Long versao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
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
		Marca other = (Marca) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

}
