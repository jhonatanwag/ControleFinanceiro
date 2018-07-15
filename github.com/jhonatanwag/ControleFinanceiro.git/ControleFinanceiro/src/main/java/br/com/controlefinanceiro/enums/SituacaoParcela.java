/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;

/**
 *
 * @author Jhonatan
 */
public enum SituacaoParcela {

	PAGO("Pago"), 
	PARCIAL("Parcial"),
	VENCIDA("Vencida"),
	AVENCER("À Vencer"),
	ADIADA("Adiada");
	
	private SituacaoParcela(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
