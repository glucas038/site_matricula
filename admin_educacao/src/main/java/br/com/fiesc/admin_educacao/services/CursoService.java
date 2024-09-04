package br.com.fiesc.admin_educacao.services;

import br.com.fiesc.admin_educacao.daos.CursoDao;
import br.com.fiesc.admin_educacao.daos.MatriculaDao;
import br.com.fiesc.admin_educacao.models.Curso;
import br.com.fiesc.admin_educacao.utils.Mensagem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class CursoService {

    @Inject
    private CursoDao cursoDao;
    
    @Inject
    private MatriculaDao matriculaDao;

    @Transactional
    public void salvar(Curso curso) {
        if (contarMatriculas(curso.getId()) <= curso.getVagasTotais()) {
            if (curso.getId() == null) {
                cursoDao.adiciona(curso);
                Mensagem.adicionarMensagemSucesso("Curso adicionado com sucesso.");
            } else {
                cursoDao.atualiza(curso);
                Mensagem.adicionarMensagemSucesso("Curso editado com sucesso.");
            }
        } else {
            Mensagem.adicionarMensagemErro("ERRO", "Vagas insuficiente. Vagas totais não pode ser menor que "
                    + contarMatriculas(curso.getId()) + ", pois há alunos matriculados.");
        }
    }

    @Transactional
    public void remover(Curso curso) {
        if (contarMatriculas(curso.getId()) == 0) {
            cursoDao.deleta(curso);
            Mensagem.adicionarMensagemSucesso("Curso deletado com sucesso.");
        } else {
            Mensagem.adicionarMensagemErro("ERRO", "Curso contém alunos matriculados.");
        }
    }

    public List<Curso> listar() {
        return cursoDao.listar();
    }

    public Curso buscaPorId(Long id) {
        return cursoDao.buscaPorId(id);
    }

    public Long contarMatriculas(Long cursoId) {
        return matriculaDao.contarMatriculasNoCurso(cursoId);
    }

    public Long getValorMin(Long cursoId) {
        return contarMatriculas(cursoId);
    }

}
