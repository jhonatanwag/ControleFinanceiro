/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;

/**
 *
 * @author Jhonatan
 */
public enum TipoConta {

BANCARIA("Bancária"), 
VIRTUAL("Vitual"), 
MOVEL("Móvel"), 
IMOVEL("Imóvel"), 
OUTROS("Outros"), 
PADRAO("Padrão");

	private TipoConta(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
