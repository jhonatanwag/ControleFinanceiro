package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.enums.Categoria;
import br.com.controlefinanceiro.enums.SituacaoCadastro;
import br.com.controlefinanceiro.model.Grupo;
import br.com.controlefinanceiro.repository.Grupos;
import br.com.controlefinanceiro.repository.filter.GrupoFilter;
import br.com.controlefinanceiro.service.CadastroGrupoService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroGrupoBean extends BeanAbstrato<Grupo> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;
	private Grupo grupo;
	private List<Grupo> listaGrupos; 

	@Inject
	private CadastroGrupoService cadastroGrupoService;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			listaGrupos = grupos.filtrados(new GrupoFilter());
			limpar();
		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Grupo());
	}

	@Override
	public void salvar() {
		setObjeto(cadastroGrupoService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Grupo salvo com sucesso!");
	}

	@Override
	public void setObjeto(Grupo objeto) {
		super.setObjeto(objeto);
		if (getObjeto() != null) {
		}
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}
	
	public SituacaoCadastro[] getSituacaoCadastros() {
		return SituacaoCadastro.values();
	}	
	
	public Categoria[] getCategorias() {
		return Categoria.values();
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	
}