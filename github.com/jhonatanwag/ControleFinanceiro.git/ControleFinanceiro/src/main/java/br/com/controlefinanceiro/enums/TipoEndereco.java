/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;

/**
 *
 * @author Danilo
 */
public enum TipoEndereco {

	RESIDENCIAL("Residencial"), COMERCIAL("Comercial"), APARTAMENTO("Apartamento"), OUTRO("Outro");

	private TipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
