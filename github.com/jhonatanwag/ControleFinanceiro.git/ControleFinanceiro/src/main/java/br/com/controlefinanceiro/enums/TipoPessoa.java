/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;

/**
 *
 * @author Jhonatan
 */
public enum TipoPessoa {

	FISICA("Física"), JURIDICA("Jurídica");

	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
