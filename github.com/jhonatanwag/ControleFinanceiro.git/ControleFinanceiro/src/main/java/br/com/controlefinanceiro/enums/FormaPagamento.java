package br.com.controlefinanceiro.enums;

public enum FormaPagamento {

	A("A vista"), P("A Prazo");

	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
}
