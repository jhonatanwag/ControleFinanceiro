package br.com.controlefinanceiro.repository;

import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.controlefinanceiro.service.IntegridadeReferencialException;
import br.com.controlefinanceiro.service.NegocioException;
import br.com.controlefinanceiro.util.jpa.Transactional;

import static javax.faces.context.FacesContext.getCurrentInstance;

public abstract class RepositoryAbstrato<T> {

	@Inject
	protected EntityManager manager;
	
	private Class<T> entityClass;
	
	
	public RepositoryAbstrato(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	
	/**
	 * 
	 * @param objeto
	 * @return
	 */
	public T salvar(T objeto) {
		//T pa = null;
		try {
			objeto = this.manager.merge(objeto);
		} catch (Exception e) {
			if (e instanceof javax.persistence.OptimisticLockException) {
				throw new IntegridadeReferencialException(getMessageFromI18N("msg.erro.integridade.referencial"));
			}
		}

		return objeto;
	}


	/**
	 * 
	 * @param id
	 * @return O Objeto por ID
	 */
	public T porId(Long id) {
		return manager.find(entityClass, id);
	}
	
	
//	@Transactional
//	public void remover(T pais) {
//		try {
//			//pais = porId(pais.getId());
//			T objeto = (T) manager.find(entityClass, id);
//			manager.remove(pais);
//			manager.flush();
//		} catch (PersistenceException e) {
//			throw new NegocioException("País não pode ser excluído.");
//		}
//	}
	
	
	@Transactional
	public void remover(Long id) {
		try {
			T objeto = (T)  porId(id);
			manager.remove(objeto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Registro não pode ser excluído.");
		}
	}
	
	
	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 *         <code>ResourceBundle</code>.
	 */
	protected String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.sistemacomercial.messages_labels",
				getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
}
