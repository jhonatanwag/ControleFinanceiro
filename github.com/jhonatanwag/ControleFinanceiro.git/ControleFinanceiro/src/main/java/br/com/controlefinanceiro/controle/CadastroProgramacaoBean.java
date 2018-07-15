package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.controlefinanceiro.enums.Categoria;
import br.com.controlefinanceiro.enums.SituacaoCadastro;
import br.com.controlefinanceiro.enums.TipoMovimentacao;
import br.com.controlefinanceiro.enums.TipoPagamento;
import br.com.controlefinanceiro.model.Programacao;
import br.com.controlefinanceiro.repository.Contas;
import br.com.controlefinanceiro.repository.Grupos;
import br.com.controlefinanceiro.repository.Pessoas;
import br.com.controlefinanceiro.repository.filter.ContaFilter;
import br.com.controlefinanceiro.repository.filter.GrupoFilter;
import br.com.controlefinanceiro.repository.filter.PessoaFilter;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Grupo;
import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.security.PessoaLogado;
import br.com.controlefinanceiro.service.CadastroProgramacaoService;
import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProgramacaoBean extends BeanAbstrato<Programacao> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroProgramacaoService cadastroProgramacaoService;
	@Inject
	@PessoaLogado
	private Pessoa pessoa;
	@Inject
	private Grupos grupos;
	@Inject
	private Contas contas;
	@Inject
	private Pessoas pessoas;
	private TreeNode treeGrupos;
	private TreeNode[] treeNodesGrupoSelecao;
	private List<Grupo> listaGrupos;
	private List<Conta> listaContas;
	private List<Conta> listaContasTransferencia;
	private List<Pessoa> listaPessoas;
	private Categoria categoria;

	@Override
	public void inicializar() {
		if (this.getObjeto() == null) {
			limpar();
		}
		listaContas = contas.porPessoa(pessoa);
		listaContasTransferencia = contas.filtrados(new ContaFilter());
		listaPessoas = pessoas.filtraFornecedor();
		// montarArvore();
	}

	@Override
	public void setObjeto(Programacao objeto) {
		super.setObjeto(objeto);
		if (getObjeto() != null) {

		}
	}

	@Override
	protected void limpar() {
		setObjeto(new Programacao());
	}

	@Override
	public void salvar() {
		setObjeto(cadastroProgramacaoService.salvar(getObjeto()));
		limpar();
		FacesUtil.addInfoMessage("Programacao salva com sucesso!");
	}

	@Override
	public boolean isEditando() {
		return this.getObjeto().getId() != null;
	}

	/* INICIO - METÓDOS */
	public void gruposPorCategoria() {
		listaGrupos = grupos.porCategoria(categoria);
	}

	/* FIM - METÓDOS */

	/* INICIO - TREE NODE */

	// Utilizando Tree no XHTML
	// <p:outputLabel value="Grupo" for="grupotree" />
	// <p:tree id="grupotree" value="#{cadastroProgramacaoBean.treeGrupos}"
	// var="doc" selectionMode="single"
	// selection="#{cadastroProgramacaoBean.treeNodesGrupoSelecao}">
	// <p:treeNode icon="ui-icon-note">
	// <h:outputText value="#{doc.toString()}" />
	// </p:treeNode>
	// <p:treeNode type="document" icon="ui-icon-document">
	// <h:outputText value="#{doc.toString()}" />
	// </p:treeNode>
	// <p:treeNode type="picture" icon="ui-icon-image">
	// <h:outputText value="#{doc.toString()}" />
	// </p:treeNode>
	// <p:treeNode type="mp3" icon="ui-icon-video">
	// <h:outputText value="#{doc.toString()}" />
	// </p:treeNode>
	// </p:tree>

	public void montarArvore() {
		treeGrupos = createDefaultGrupos();
	}

	private TreeNode createDefaultGrupos() {
		listaGrupos = grupos.filtrados(new GrupoFilter());

		TreeNode root = new DefaultTreeNode("Root Node", null);
		TreeNode grupo = null;
		TreeNode subGrupo = null;

		for (Grupo gr : listaGrupos) {
			// Create child node
			if (gr.getGrupoPai() == null) {
				if (!gr.getJaNaArvore()) {
					grupo = new DefaultTreeNode(gr, root);
					gr.setJaNaArvore(true);
					grupo.setParent(root);
				} else {
					grupo = getNode(root, gr);
				}
			} else {
				if (!gr.getGrupoPai().getJaNaArvore()) {
					grupo = new DefaultTreeNode(gr.getGrupoPai(), root);
					gr.getGrupoPai().setJaNaArvore(true);
					grupo.setParent(root);
				} else {
					grupo = getNode(root, gr.getGrupoPai());
				}
				if (!gr.getJaNaArvore()) {
					subGrupo = new DefaultTreeNode(gr, grupo);
					gr.setJaNaArvore(true);
					subGrupo.setParent(grupo);
				}
			}
		}
		return root;
	}

	public TreeNode getNode(TreeNode rootNode, Object searchNode) {
		DefaultTreeNode foundTreeNode = null;
		for (TreeNode tn : rootNode.getChildren()) {
			if (searchNode instanceof Grupo) {
				if (tn.getData().equals(searchNode)) {
					foundTreeNode = (DefaultTreeNode) tn;
					break;
				}
			}

			if (searchNode instanceof Grupo) {
				for (TreeNode tn2 : tn.getChildren()) {
					if (tn2.getData().equals(searchNode)) {
						foundTreeNode = (DefaultTreeNode) tn2;
						break;
					}
				}
			}

		}
		return foundTreeNode;
	}
	/* FIM - TREE NODE */

	/* INICIO - ENUNS */

	public TipoMovimentacao[] getTipoMovimentacaos() {
		return TipoMovimentacao.values();
	}

	public TipoPagamento[] getTipoPagamentos() {
		return TipoPagamento.values();
	}

	public SituacaoCadastro[] getSituacaoCadastros() {
		return SituacaoCadastro.values();
	}

	public Categoria[] getCategorias() {
		return Categoria.values();
	}
	/* FIM - ENUNS */

	/* INICIO - GET E SET */
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public List<Conta> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<Conta> listaContas) {
		this.listaContas = listaContas;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<Conta> getListaContasTransferencia() {
		return listaContasTransferencia;
	}

	public void setListaContasTransferencia(List<Conta> listaContasTransferencia) {
		this.listaContasTransferencia = listaContasTransferencia;
	}

	public TreeNode getTreeGrupos() {
		return treeGrupos;
	}

	public void setTreeGrupos(TreeNode treeGrupos) {
		this.treeGrupos = treeGrupos;
	}

	public TreeNode[] getTreeNodesGrupoSelecao() {
		return treeNodesGrupoSelecao;
	}

	public void setTreeNodesGrupoSelecao(TreeNode[] treeNodesGrupoSelecao) {
		this.treeNodesGrupoSelecao = treeNodesGrupoSelecao;
	}
	/* FIM - GET E SET */
}