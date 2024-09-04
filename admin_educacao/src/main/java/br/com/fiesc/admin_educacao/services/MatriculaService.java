package br.com.fiesc.admin_educacao.services;

import br.com.fiesc.admin_educacao.daos.MatriculaDao;
import br.com.fiesc.admin_educacao.models.Curso;
import br.com.fiesc.admin_educacao.models.Matricula;
import br.com.fiesc.admin_educacao.models.Pessoa;
import br.com.fiesc.admin_educacao.utils.Mensagem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class MatriculaService {

    @Inject
    private MatriculaDao matriculaDao;

    @Inject
    private CursoService cursoService;

    @Inject
    private PessoaService pessoaService;

    @Transactional
    public int salvar(Matricula matricula) {
        if (matricula.getPessoa() == null || matricula.getPessoa().getId() == null) {
            Mensagem.adicionarMensagemErro("Erro", "Pessoa não informada.");
            return 1;
        }

        if (matricula.getCurso() == null || matricula.getCurso().getId() == null) {
            Mensagem.adicionarMensagemErro("Erro", "Curso não informado.");
            return 1;
        }

        Pessoa pessoa = pessoaService.buscaPorId(matricula.getPessoa().getId());
        Curso curso = cursoService.buscaPorId(matricula.getCurso().getId());

        if (pessoa == null) {
            Mensagem.adicionarMensagemErro("Erro", "Pessoa não encontrada.");
            return 1;
        }

        if (curso == null) {
            Mensagem.adicionarMensagemErro("Erro", "Curso não encontrado.");
            return 1;
        }

        matricula.setPessoa(pessoa);
        matricula.setCurso(curso);

        if (!verificarIdadeMinima(matricula, curso)) {
            Mensagem.adicionarMensagemErro("Idade mínima.", "Idade mínima não atingida.");
            return 1;
        }

        if (!verificarVagasDisponiveis(curso)) {
            Mensagem.adicionarMensagemErro("Curso lotado.", "Não há vagas disponíveis neste curso.");
            return 1;
        }
        
        if (!verificarDataMatricula(matricula, curso)) {
            Mensagem.adicionarMensagemErro("Data de matrícula inválida.", "A data de matrícula não pode ser posterior ao início do curso.");
            return 1;
        }

        Matricula existente = matriculaDao.buscarPorAlunoECurso(curso.getId(), pessoa.getId());

        if (matricula.getId() == null) {
            if (existente == null) {
                matriculaDao.adiciona(matricula);
                Mensagem.adicionarMensagemSucesso("Matrícula realizada com sucesso!");
            } else {
                Mensagem.adicionarMensagemErro("Aluno já matriculado.", "O aluno já está matriculado neste curso.");
                return 1;
            }
        } else {
            if (existente == null || existente.getId().equals(matricula.getId())) {
                matriculaDao.atualiza(matricula);
                Mensagem.adicionarMensagemSucesso("Matrícula atualizada com sucesso!");
            } else {
                Mensagem.adicionarMensagemErro("Aluno já matriculado.", "O aluno já está matriculado neste curso.");
                return 1;
            }
        }
        return 0;
    }

    @Transactional
    public void remover(Matricula matricula) {
        matriculaDao.deleta(matricula);
        Mensagem.adicionarMensagemSucesso("Matrícula removida com sucesso!");
    }

    public List<Matricula> listar() {
        return matriculaDao.listar();
    }

    public Matricula buscaPorId(Long id) {
        return matriculaDao.buscaPorId(id);
    }

    private boolean verificarIdadeMinima(Matricula matricula, Curso curso) {
        Long idade = matricula.getPessoa().calcularIdade(matricula.getDtMatricula());
        return idade >= curso.getIdadeMin();
    }

    private boolean verificarVagasDisponiveis(Curso curso) {
        Long matriculasNoCurso = matriculaDao.contarMatriculasNoCurso(curso.getId());
        return matriculasNoCurso < curso.getVagasTotais();
    }

    private boolean verificarDataMatricula(Matricula matricula, Curso curso) {
        return !matricula.getDtMatricula().after(curso.getDtInicio());
    }
}
