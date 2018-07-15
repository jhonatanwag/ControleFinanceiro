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

import br.com.controlefinanceiro.model.Programacao;
import br.com.controlefinanceiro.repository.filter.ProgramacaoFilter;

public class Programacaos extends RepositoryAbstrato<Programacao> implements Serializable {
	
	public Programacaos() {
		super(Programacao.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Programacao salvar(Programacao objeto) {
		return super.salvar(objeto);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Programacao> filtrados(ProgramacaoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Programacao.class);
		
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
		return criteria.addOrder(Order.asc("descricao")).list();
	}	

	public List<Programacao> porNome(String nome) {
		return this.manager.createQuery("from Programacao where upper(descricao) like :nome", Programacao.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Programacao porDescricaoUnico(String descricao, Long id) {
		try {
			return manager.createQuery("from Programacao where upper(descricao) = :descricao and id <> :id", Programacao.class)
					.setParameter("descricao", descricao.toUpperCase()).setParameter("id", (id == null ? -1 : id))
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
