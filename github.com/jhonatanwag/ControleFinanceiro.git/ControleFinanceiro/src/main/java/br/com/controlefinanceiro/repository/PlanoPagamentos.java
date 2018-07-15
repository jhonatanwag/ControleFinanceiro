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

import br.com.controlefinanceiro.model.PlanoPagamento;
import br.com.controlefinanceiro.repository.filter.PlanoPagamentoFilter;

public class PlanoPagamentos extends RepositoryAbstrato<PlanoPagamento> implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlanoPagamentos() {
		super(PlanoPagamento.class);
	}

	@Override
	public PlanoPagamento salvar(PlanoPagamento objeto) {
		return super.salvar(objeto);
	}

	@SuppressWarnings("unchecked")
	public List<PlanoPagamento> filtrados(PlanoPagamentoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PlanoPagamento.class);

		if (filtro.getIdDe() != null && filtro.getIdAte() != null) {
			criteria.add(Restrictions.between("id", filtro.getIdDe(), filtro.getIdAte()));
		}

		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("descricao")).list();
	}

	public PlanoPagamento porDescricaoUnico(String descricao) {
		try {
			return manager.createQuery("from PlanoPagamento where upper(descricao) = :descricao", PlanoPagamento.class)
					.setParameter("descricao", descricao.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<PlanoPagamento> porFormaPagamento(String formaPagamento) {
		try {
			return manager.createQuery("from PlanoPagamento where forma_pagamento = :formaPagamento", PlanoPagamento.class)
					.setParameter("formaPagamento", formaPagamento).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
