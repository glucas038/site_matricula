package br.com.fiesc.admin_educacao.beans;

import br.com.fiesc.admin_educacao.models.Pessoa;
import br.com.fiesc.admin_educacao.services.PessoaService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas;
	private Integer pessoaId;

	@Inject
	private PessoaService pessoaService;

	@Transactional
	public void salvar() {
		int flag = pessoaService.salvar(pessoa);
		if (flag == 0) {
			limparEAtualizar();
		}
	}

	@Transactional
	public void remover(Pessoa pessoa) {
		pessoaService.remover(pessoa);
		pessoas = pessoaService.listar();
	}

	public List<Pessoa> getPessoas() {
		if (pessoas == null) {
			pessoas = pessoaService.listar();
		}
		return pessoas;
	}

	public void carregar(Pessoa pessoa) {
		this.pessoa = pessoaService.buscaPorId(pessoa.getId());
	}

	public void limparEAtualizar() {
		pessoa = new Pessoa();
		pessoas = pessoaService.listar();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}
}
