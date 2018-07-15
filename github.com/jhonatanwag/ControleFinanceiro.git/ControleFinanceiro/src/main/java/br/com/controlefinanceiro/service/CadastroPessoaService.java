package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.model.Email;
import br.com.controlefinanceiro.model.Enderecos;
import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.model.Telefone;
import br.com.controlefinanceiro.repository.Pessoas;
import br.com.controlefinanceiro.util.jpa.Transactional;

public class CadastroPessoaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;

	@Transactional
	public Pessoa salvar(Pessoa pes) {
		Pessoa pessoaExiste = pessoas.porDocEstadualUnico(pes.getDocumentoEstadual(), pes.getId());

		if (pessoaExiste != null && !pessoaExiste.equals(pes)) {
			throw new NegocioException("Já existe uma pessoa com o mesmo documento estadual cadastrado.");
		}

		pessoaExiste = pessoas.porDocFedUnico(pes.getDocumentoFederal(), pes.getId());
		if (pessoaExiste != null && !pessoaExiste.equals(pes)) {
			throw new NegocioException("Já existe uma pessoa com o mesmo documento federal cadastrado.");
		}

		return pessoas.salvar(pes);
	}

	@Transactional
	public void excluirEndereco(Enderecos enderecos) {
		if (!pessoas.validaExclusaoEndereco(enderecos)) {
			throw new NegocioException("Endereço não pode ser excluído.");
		}
	}

	@Transactional
	public void excluirEmail(Email email) {
		if (!pessoas.validaExclusaoEmail(email)) {
			throw new NegocioException("Email não pode ser excluído.");
		}
	}

	@Transactional
	public void excluirTelefone(Telefone telefone) {
		if (!pessoas.validaExclusaoTelefone(telefone)) {
			throw new NegocioException("Telefone não pode ser excluído.");
		}
	}

}
