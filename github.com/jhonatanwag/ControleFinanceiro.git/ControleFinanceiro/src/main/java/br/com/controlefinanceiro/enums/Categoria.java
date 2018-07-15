/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;

/**
 *
 * @author Jhonatan
 */
public enum Categoria {

	EDUCACAO("Educação"), 
	SAUDE("Saúde"), 
	AUTOMOVEL("Automóvel"), 
	INVESTIMENTO("Investimento"), 
	GERAIS("Gerais"), 
	ALIMENTACAO("Alimentação"),
	DOMESTICA("Doméstica"),
	IMOVEL("Imóvel");

	private Categoria(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
