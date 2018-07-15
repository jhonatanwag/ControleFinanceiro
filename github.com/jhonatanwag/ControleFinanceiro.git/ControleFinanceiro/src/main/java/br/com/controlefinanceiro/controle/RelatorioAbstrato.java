package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

public abstract class RelatorioAbstrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected FacesContext facesContext;

	@Inject
	protected HttpServletResponse response;

	@Inject
	protected EntityManager manager;

	/**
	 * 
	 * 
	 * Método utilizadao para emitir o relatorio!
	 * 
	 * 
	 */
	public abstract void emitir();

}