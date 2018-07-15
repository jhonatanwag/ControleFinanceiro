package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.model.Cidade;
import br.com.controlefinanceiro.model.Estado;
import br.com.controlefinanceiro.repository.Estados;
import br.com.controlefinanceiro.repository.filter.EstadoFilter;
import br.com.controlefinanceiro.service.CadastroCidadeService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCidadeBean extends BeanAbstrato<Cidade>implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Estados estados;
	@Inject
	private CadastroCidadeService cadastroCidadeService;
	private Estado estado;
	private List<Estado> todosEstados;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
		getObjeto().setDataCadastro(new Date());
		todosEstados = estados.filtrados(new EstadoFilter());

	}

	@Override
	public void setObjeto(Cidade objeto) {
		super.setObjeto(objeto);
		if (getObjeto() != null) {
			if (getObjeto().getEstado() != null) {
				this.estado = getObjeto().getEstado();
			}
		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Cidade());
		this.estado = null;
	}

	@Override
	public void salvar() {
		setObjeto(cadastroCidadeService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Cidade salva com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public void setTodosEstados(List<Estado> todosEstados) {
		this.todosEstados = todosEstados;
	}

}