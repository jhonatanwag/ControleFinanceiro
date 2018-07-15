package br.com.controlefinanceiro.controle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class BeanAbstrato<T extends Object> {

	protected static Log log = LogFactory.getLog(BeanAbstrato.class);
	
	private T objeto;

	public BeanAbstrato() {
		super();
		limpar();
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
		if (this.objeto != null) {
		}
	}

	public abstract void inicializar();
	
	protected abstract void limpar();
	
	public abstract void salvar();
	
	public abstract boolean isEditando();

}
