package br.com.fiesc.admin_educacao.beans;

import br.com.fiesc.admin_educacao.models.Curso;
import br.com.fiesc.admin_educacao.services.CursoService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Curso curso = new Curso();
    private List<Curso> cursos;
    private Integer cursoId;

    @Inject
    private CursoService cursoService;

    public void salvar() {
        cursoService.salvar(curso);
        limparEAtualizar();
    }

    public void remover(Curso curso) {
        cursoService.remover(curso);
        cursos = cursoService.listar();
    }

    public List<Curso> getCursos() {
        if (cursos == null) {
            cursos = cursoService.listar();
        }
        return cursos;
    }

    public void carregar(Curso curso) {
        this.curso = cursoService.buscaPorId(curso.getId());
    }

    public Long getValorMin() {
        if (curso != null && curso.getId() != null) {
            return cursoService.getValorMin(curso.getId());
        }
        return null;
    }

    public void limparEAtualizar() {
        curso = new Curso();
        cursos = cursoService.listar();
    }


    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

}
