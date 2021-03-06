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

import br.com.controlefinanceiro.model.Cor;
import br.com.controlefinanceiro.repository.filter.CorFilter;

public class Cores extends RepositoryAbstrato<Cor> implements Serializable {

	public Cores() {
		super(Cor.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Cor salvar(Cor objeto) {
		return super.salvar(objeto);
	}

	@SuppressWarnings("unchecked")
	public List<Cor> filtrados(CorFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cor.class);

		if (filtro.getIdDe() != null && filtro.getIdAte() != null) {
			criteria.add(Restrictions.between("id", filtro.getIdDe(), filtro.getIdAte()));
		}

		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("descricao")).list();
	}

	public Cor porDescricaoUnico(String descricao, Long id) {
		try {
			return manager.createQuery("from Cor where upper(descricao) = :descricao and id <> :id", Cor.class)
					.setParameter("descricao", descricao.toUpperCase()).setParameter("id", (id == null ? -1 : id))
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Cor> porNome(String descricao) {
		return this.manager.createQuery("from Cor where upper(descricao) like :descricao", Cor.class)
				.setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
	}

}
