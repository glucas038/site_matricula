package br.com.fiesc.admin_educacao.beans;

import br.com.fiesc.admin_educacao.models.Curso;
import br.com.fiesc.admin_educacao.models.Matricula;
import br.com.fiesc.admin_educacao.models.Pessoa;
import br.com.fiesc.admin_educacao.services.CursoService;
import br.com.fiesc.admin_educacao.services.MatriculaService;
import br.com.fiesc.admin_educacao.services.PessoaService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MatriculaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Matricula matricula = new Matricula();
	private List<Matricula> matriculas;
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas;
	private Curso curso = new Curso();
	private List<Curso> cursos;

	@Inject
	private MatriculaService matriculaService;

	@Inject
	private CursoService cursoService;

	@Inject
	private PessoaService pessoaService;

	@Transactional
	public void salvar() {
		matricula.setPessoa(pessoa);
		matricula.setCurso(curso);
		int flag = matriculaService.salvar(matricula);
		if (flag == 0) {
			limparEAtualizar();
		}
	}

	@Transactional
	public void remover(Matricula matricula) {
		matriculaService.remover(matricula);
		matriculas = matriculaService.listar();
	}

	public List<Matricula> getMatriculas() {
		if (matriculas == null) {
			matriculas = matriculaService.listar();
		}
		return matriculas;
	}

	public void carregar(Matricula matricula) {
		this.matricula = matriculaService.buscaPorId(matricula.getId());
		this.pessoa = matricula.getPessoa();
		this.curso = matricula.getCurso();
	}

	public void limparEAtualizar() {
		matricula = new Matricula();
		curso = new Curso();
		pessoa = new Pessoa();
		matriculas = matriculaService.listar();
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		if (pessoas == null) {
			pessoas = pessoaService.listar();
		}
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		if (cursos == null) {
			cursos = cursoService.listar();
		}
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}
