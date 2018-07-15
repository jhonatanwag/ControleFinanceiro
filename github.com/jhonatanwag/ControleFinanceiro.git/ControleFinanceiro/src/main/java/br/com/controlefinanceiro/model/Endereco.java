/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
//import javax.validation.constraints.NotNull;

/**
 *
 * @author jhonatan
 */
@Embeddable
public class Endereco implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	@Column(length = 60)
	private String logradouro;
	@Column(length = 10)
	private String numero;
	@Column(length = 60)
	private String bairro;
	@Column(length = 60)
	private String complemento;
	@Column(length = 9)
	private String cep;
	@Transient
	private String enderecoCompleto;

	public Endereco() {
	}

	public Endereco(Cidade cidade, String logradouro, String numero, String bairro, String complemento, String cep) {
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCepSemMascara() {
		return cep.replace("-", "").replace(".", "");
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getEnderecoCompleto() {
		this.enderecoCompleto = this.logradouro + ", " + this.numero + " " + this.complemento + " - " + this.bairro
				+ " " + "CEP: " + this.cep + " - " + this.cidade.getNome() + " - " + this.cidade.getEstado().getUf();
		return this.enderecoCompleto;
	}
}
