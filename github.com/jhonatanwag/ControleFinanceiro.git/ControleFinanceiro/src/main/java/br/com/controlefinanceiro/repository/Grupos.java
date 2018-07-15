package br.com.controlefinanceiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.controlefinanceiro.enums.Categoria;
import br.com.controlefinanceiro.model.Grupo;
import br.com.controlefinanceiro.repository.filter.GrupoFilter;

public class Grupos extends RepositoryAbstrato<Grupo> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Grupos() {
		super(Grupo.class);
	}

	@Override
	public Grupo salvar(Grupo objeto) {
		return super.salvar(objeto);
	}

	@SuppressWarnings("unchecked")
	public List<Grupo> filtrados(GrupoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Grupo.class);

		if (filtro.getIdDe() != null && filtro.getIdAte() != null) {
			criteria.add(Restrictions.between("id", filtro.getIdDe(), filtro.getIdAte()));
		}

		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataCadastroInicio() != null && filtro.getDataCadastroFim() != null) {
			criteria.add(
					Restrictions.between("dataCadastro", filtro.getDataCadastroInicio(), filtro.getDataCadastroFim()));
		}
		if (filtro.getVendaGourmet() != null) {
			criteria.add(Restrictions.eq("staVendaGourmet", filtro.getVendaGourmet()));
		}

		return criteria.addOrder(Order.asc("descricao")).list();
	}
	
	public Grupo porDescricaoUnico(String descricao, Long id) {
		try {
			return manager.createQuery("from Grupo where upper(descricao) = :descricao and id <> :id", Grupo.class)
					.setParameter("descricao", descricao.toUpperCase()).setParameter("id", (id == null ? -1 : id))
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Grupo> porNome(String descricao) {
		return this.manager.createQuery("from Grupo where upper(descricao) like :descricao", Grupo.class)
				.setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
	}
	
	public List<Grupo> porCategoria(Categoria categoria) {
		return this.manager.createQuery("from Grupo where categoria = :cat", Grupo.class)
				.setParameter("cat", categoria).getResultList();
	}

}
