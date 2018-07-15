/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;

/**
 *
 * @author Jhonatan
 */
public enum TipoEmail {

	COMERCIAL("Comercial"), PESSOAL("Pessoal");
	
	private TipoEmail(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
