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

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Cor;
import br.com.controlefinanceiro.model.Estado;
import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.repository.filter.ContaFilter;

public class Contas extends RepositoryAbstrato<Conta> implements Serializable {
	
	public Contas() {
		super(Conta.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Conta salvar(Conta objeto) {
		return super.salvar(objeto);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Conta> filtrados(ContaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Conta.class);
		
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

	public List<Conta> porNome(String nome) {
		return this.manager.createQuery("from Conta where upper(descricao) like :nome", Conta.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Conta porDescricaoUnico(String descricao, Long id) {
		try {
			return manager.createQuery("from Conta where upper(descricao) = :descricao and id <> :id", Conta.class)
					.setParameter("descricao", descricao.toUpperCase()).setParameter("id", (id == null ? -1 : id))
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Conta> porPessoa(Pessoa pessoa) {
		return this.manager.createQuery("from Conta where pessoa = :pessoa", Conta.class)
				.setParameter("pessoa", pessoa).getResultList();
	}

}
