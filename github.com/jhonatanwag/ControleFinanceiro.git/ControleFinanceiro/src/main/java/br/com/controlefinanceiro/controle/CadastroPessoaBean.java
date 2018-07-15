package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.enums.Sexo;
import br.com.controlefinanceiro.enums.TipoEmail;
import br.com.controlefinanceiro.enums.TipoEndereco;
import br.com.controlefinanceiro.enums.TipoPessoa;
import br.com.controlefinanceiro.enums.TipoTelefone;
import br.com.controlefinanceiro.model.Cidade;
import br.com.controlefinanceiro.model.Email;
import br.com.controlefinanceiro.model.Enderecos;
import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.model.Telefone;
import br.com.controlefinanceiro.repository.Cidades;
import br.com.controlefinanceiro.repository.filter.CidadeFilter;
import br.com.controlefinanceiro.service.CadastroPessoaService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPessoaBean extends BeanAbstrato<Pessoa> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Cidades cidades;	
	@Inject
	private CadastroPessoaService cadastroPessoaService;
	@Inject
	EnvioEmailPessoaBean envioEmailPessoaBean;
	private List<Cidade> todasCidades;

	private String mascaraTipoPessoa;
	private Enderecos enderecoSelecionado;
	private Email emailSelecionado;
	private Telefone telefoneSelecionado;

	private Boolean editandoEndereco;
	private Boolean editandoEmail;
	private Boolean editandoTelefone;
	private Boolean editandoInfBancaria;
	private Boolean editandoCliBloqAviso;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
		todasCidades = cidades.filtrados(new CidadeFilter());
	}

	@Override
	public void setObjeto(Pessoa objeto) {
		super.setObjeto(objeto);
		if (getObjeto() != null) {
		}
	}

	@Produces
	@PessoaEdicao
	@Override
	public Pessoa getObjeto() {
		// TODO Auto-generated method stub
		return super.getObjeto();
	}

	@Override
	protected void limpar() {
		setObjeto(new Pessoa());
		getObjeto().setDataCadastro(new Date());
		getObjeto().setTipoPessoa(TipoPessoa.FISICA);
		this.mascaraTipoPessoa = "999.999.999-99";
	}

	@Override
	public void salvar() {
		setObjeto(cadastroPessoaService.salvar(getObjeto()));
		// envioEmailPessoaBean.enviarEmail();
		limpar();
		FacesUtil.addInfoMessage("Pessoa salvo com sucesso!");
	}

	public List<Cidade> getTodasCidades() {
		return todasCidades;
	}

	public Enderecos getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Enderecos enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public Email getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(Email emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}

	public Telefone getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	public TipoPessoa[] getTipoPessoas() {
		return TipoPessoa.values();
	}

	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public TipoEndereco[] getTipoEnderecos() {
		return TipoEndereco.values();
	}

	public TipoEmail[] getTipoEmails() {
		return TipoEmail.values();
	}

	public TipoTelefone[] getTipoTelefones() {
		return TipoTelefone.values();
	}	

	public void defineTipoPessoa() {
		if (getObjeto().getTipoPessoa() != null) {
			if (getObjeto().getTipoPessoa().equals(TipoPessoa.FISICA)) {
				this.mascaraTipoPessoa = "999.999.999-99";
				if (!isEditando()) {
					getObjeto().setRazaoSocial(null);
				}
			} else {
				this.mascaraTipoPessoa = "99.999.999/9999-99";
				if (!isEditando()) {
					Integer i = null;
				}

			}
		}
	}

	public void defineFuncionario() {
		if (getObjeto().isFuncionario()) {

		} else {

		}

	}

	public String getMascaraTipoPessoa() {
		return mascaraTipoPessoa;
	}

	/* Endereço - inicio */

	public void novoEndereco() {
		setEnderecoSelecionado(new Enderecos());
		getEnderecoSelecionado().setPessoa(getObjeto());
		editandoEndereco = false;
	}

	public void alterarEndereco() {
		this.editandoEndereco = true;
	}

	public void confirmarEndereco() {
		int i = 0;
		if (!editandoEndereco) {
			getObjeto().getEnderecos().add(getEnderecoSelecionado());
		}
		for (Enderecos enderecos : getObjeto().getEnderecos()) {
			enderecos.setSequencia(++i);
		}

	}

	public void excluirEndereco() {
		cadastroPessoaService.excluirEndereco(getEnderecoSelecionado());
		getObjeto().getEnderecos().remove(getEnderecoSelecionado());
	}

	/* Endereco - fim */

	/* Email - inicio */

	public void novoEmail() {
		setEmailSelecionado(new Email());
		getEmailSelecionado().setPessoa(getObjeto());
		editandoEmail = false;
	}

	public void alterarEmail() {
		this.editandoEmail = true;
	}

	public void confirmarEmail() {
		int i = 0;
		if (!editandoEmail) {
			getObjeto().getEmails().add(getEmailSelecionado());
		}
		for (Email emails : getObjeto().getEmails()) {
			emails.setSequencia(++i);
		}

	}

	public void excluirEmail() {
		cadastroPessoaService.excluirEmail(getEmailSelecionado());
		getObjeto().getEmails().remove(getEmailSelecionado());
	}

	/* Email - fim */

	/* Telefone - inicio */

	public void novoTelefone() {
		setTelefoneSelecionado(new Telefone());
		getTelefoneSelecionado().setPessoa(getObjeto());
		editandoTelefone = false;
	}

	public void alterarTelefone() {
		this.editandoTelefone = true;
	}

	public void confirmarTelefone() {
		int i = 0;
		if (!editandoTelefone) {
			getObjeto().getTelefones().add(getTelefoneSelecionado());
		}
		for (Telefone telefones : getObjeto().getTelefones()) {
			telefones.setSequencia(++i);
		}

	}

	public void excluirTelefone() {
		cadastroPessoaService.excluirTelefone(getTelefoneSelecionado());
		getObjeto().getTelefones().remove(getTelefoneSelecionado());
	}

	/* Telefone - fim */
}
