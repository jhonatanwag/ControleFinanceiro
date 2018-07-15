package br.com.controlefinanceiro.controle;

import java.util.List;

import br.com.controlefinanceiro.repository.filter.Filter;

public abstract class PesquisaBeanAbstrato<T, F extends Filter> {

	private Filter filtro;
	private List<T> objetosFiltrados;
	private T objetoSelecionado;

	@SuppressWarnings("unchecked")
	public F getFiltro() {
		return (F) filtro;
	}

	public void setFiltro(Filter filtro) {
		this.filtro = filtro;
	}

	public List<T> getObjetosFiltrados() {
		return objetosFiltrados;
	}

	public void setObjetosFiltrados(List<T> objetosFiltrados) {
		this.objetosFiltrados = objetosFiltrados;
	}

	public T getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(T objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public abstract void pesquisar();

	public abstract void excluir();

}
