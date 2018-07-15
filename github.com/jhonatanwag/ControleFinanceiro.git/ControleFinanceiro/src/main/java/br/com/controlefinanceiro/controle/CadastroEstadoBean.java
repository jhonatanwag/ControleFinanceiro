package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Estado;
import br.com.controlefinanceiro.model.Pais;
import br.com.controlefinanceiro.repository.Paises;
import br.com.controlefinanceiro.repository.filter.PaisFilter;
import br.com.controlefinanceiro.service.CadastroEstadoService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstadoBean extends BeanAbstrato<Estado> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Paises paises;
	@Inject
	private CadastroEstadoService cadastroEstadoService;
	private Pais pais;
	private List<Pais> todosPaises;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
		todosPaises = paises.filtrados(new PaisFilter());

	}

	@Override
	public void setObjeto(Estado objeto) {
		super.setObjeto(objeto);
		if (getObjeto() != null) {
			if (getObjeto().getPais() != null) {
				this.pais = getObjeto().getPais();
			}
		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Estado());
		this.pais = null;
	}

	@Override
	public void salvar() {
		setObjeto(cadastroEstadoService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Estado salvo com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Pais> getTodosPaises() {
		return todosPaises;
	}

	public void setTodosPaises(List<Pais> todosPaises) {
		this.todosPaises = todosPaises;
	}

}