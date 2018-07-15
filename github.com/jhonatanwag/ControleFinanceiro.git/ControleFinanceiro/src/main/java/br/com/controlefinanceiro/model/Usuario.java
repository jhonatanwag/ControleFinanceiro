/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.controlefinanceiro.util.Criptografia;

//import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Jhonatan
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "foreignUsuario")
	@GenericGenerator(name = "foreignUsuario", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "pessoa") })
	@Column(name = "usuario_id")
	private Long id;

	@OneToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@Column(length = 40, nullable = false, unique = true)
	private String login;
	@Column(length = 100, nullable = false, name = "senha")
	private String senha;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dat_ult_acesso", nullable = true)
	private Date dataUltAcesso = new Date();
	@Transient
	private String novaSenha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = Criptografia.criptografar(novaSenha.toLowerCase());
		this.senha = this.novaSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Criptografia.criptografar(senha);
	}

	public Date getDataUltAcesso() {
		return dataUltAcesso;
	}

	public void setDataUltAcesso(Date dataUltAcesso) {
		this.dataUltAcesso = dataUltAcesso;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuario other = (Usuario) obj;
		if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + (this.login != null ? this.login.hashCode() : 0);
		return hash;
	}
}
