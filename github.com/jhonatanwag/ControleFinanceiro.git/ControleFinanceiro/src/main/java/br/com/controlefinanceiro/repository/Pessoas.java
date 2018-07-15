package br.com.controlefinanceiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.controlefinanceiro.model.Email;
import br.com.controlefinanceiro.model.Enderecos;
import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.model.Telefone;
import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.repository.filter.PessoaFilter;
import br.com.controlefinanceiro.repository.filter.UsuarioFilter;

public class Pessoas extends RepositoryAbstrato<Pessoa> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	public Pessoas() {
		super(Pessoa.class);
	}

	@Override
	public Pessoa salvar(Pessoa objeto) {
		return super.salvar(objeto);
	}

	public Usuario salvar(Usuario objeto) {
		return usuarios.salvar(objeto);
	}

	public void remover(Usuario usuario) {
		usuarios.remover(usuario.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> filtrados(PessoaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);

		if (filtro.getIdDe() != null && filtro.getIdAte() != null) {
			criteria.add(Restrictions.between("id", filtro.getIdDe(), filtro.getIdAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getRazaoSocial())) {
			criteria.add(Restrictions.ilike("razaoSocial", filtro.getRazaoSocial(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataCadastroInicio() != null && filtro.getDataCadastroFim() != null) {
			criteria.add(
					Restrictions.between("dataCadastro", filtro.getDataCadastroInicio(), filtro.getDataCadastroFim()));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (filtro.getIdDe() != null && filtro.getIdAte() != null) {
			criteria.add(Restrictions.between("id", filtro.getIdDe(), filtro.getIdAte()));
		}

		if (StringUtils.isNotBlank(filtro.getLogin())) {
			criteria.add(Restrictions.ilike("login", filtro.getLogin(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("login")).list();
	}

	public Usuario porIdUsuario(Long id) {
		return manager.find(Usuario.class, id);
	}

	public Pessoa porDocFedUnico(String docFed, Long id) {
		try {
			return manager.createQuery("from Pessoa where documentoFederal = :docFed and id <> :id", Pessoa.class)
					.setParameter("docFed", docFed).setParameter("id", (id == null ? -1 : id)).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Pessoa porDocEstadualUnico(String docEst, Long id) {
		try {
			return manager.createQuery("from Pessoa where documentoEstadual = :docEst and id <> :id", Pessoa.class)
					.setParameter("docEst", docEst.toUpperCase()).setParameter("id", (id == null ? -1 : id))
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Pessoa> porNomeFantasia(String nome) {
		return this.manager.createQuery("from Pessoa where upper(nome) like :nome", Pessoa.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public List<Pessoa> filtraFornecedor() {
		return this.manager.createQuery("from Pessoa where fornecedor = 'TRUE'", Pessoa.class)
				.getResultList();
	}

	public List<Pessoa> filtraCliente() {
		return this.manager.createQuery("from Pessoa where cliente = 'TRUE'", Pessoa.class)
				.getResultList();
	}
	
	public List<Pessoa> filtraFuncionario() {
		return this.manager.createQuery("from Pessoa where funcionario = 'TRUE'", Pessoa.class)
				.getResultList();
	}
	
	public List<Email> listaEmailsPessoaPorId(Long id) {
		return this.manager.createQuery("from Email where pessoa.id = :id", Email.class).setParameter("id", id)
				.getResultList();
	}

	public Boolean validaExclusaoEndereco(Enderecos enderecos) {
		return true;
	}

	public boolean validaExclusaoTelefone(Telefone telefone) {
		return true;
	}

	public boolean validaExclusaoEmail(Email email) {
		return true;
	}

	public Usuario porLoginUsuarioUnico(String login, Long id) {
		try {
			return manager.createQuery("from Usuario where login = :login and id <> :id", Usuario.class)
					.setParameter("login", login.toUpperCase()).setParameter("id", (id == null ? -1 : id))
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
