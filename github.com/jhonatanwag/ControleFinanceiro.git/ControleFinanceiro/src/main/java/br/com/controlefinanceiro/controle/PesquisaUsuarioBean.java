package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.repository.Pessoas;
import br.com.controlefinanceiro.repository.filter.UsuarioFilter;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean extends PesquisaBeanAbstrato<Usuario, UsuarioFilter>implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas usuarios;

	public PesquisaUsuarioBean() {
		setFiltro(new UsuarioFilter());
	}

	@Override
	public void excluir() {
		usuarios.remover(getObjetoSelecionado());
		getObjetosFiltrados().remove(getObjetoSelecionado());
		FacesUtil.addInfoMessage("Usuario " + getObjetoSelecionado().getLogin() + " excluído com sucesso.");
	}

	@Override
	public void pesquisar() {
		setObjetosFiltrados(usuarios.filtrados(getFiltro()));
	}

}