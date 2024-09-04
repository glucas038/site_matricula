package br.com.fiesc.admin_educacao.services;

import br.com.fiesc.admin_educacao.daos.PessoaDao;
import br.com.fiesc.admin_educacao.daos.MatriculaDao;
import br.com.fiesc.admin_educacao.models.Pessoa;
import br.com.fiesc.admin_educacao.utils.ManipulaString;
import br.com.fiesc.admin_educacao.utils.Mensagem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class PessoaService {

    @Inject
    private PessoaDao pessoaDao;

    @Inject
    private MatriculaDao matriculaDao;

    @Transactional
    public int salvar(Pessoa pessoa) {
    	
    	 Pessoa pessoaExistente = pessoaDao.buscarPorCpf(pessoa.getCpf());
         
         if (pessoaExistente != null) {
             if (pessoa.getId() == null || !pessoaExistente.getId().equals(pessoa.getId())) {
                 Mensagem.adicionarMensagemErro("Erro", "CPF já cadastrado.");
                 return 1;
             }
         }
    	
        if (pessoa.getId() == null) {
        	pessoa.setNome(ManipulaString.letraMaiuscula(pessoa.getNome()));
            pessoaDao.adiciona(pessoa);
            Mensagem.adicionarMensagemSucesso("Pessoa adicionada com sucesso.");
        } else {
            pessoaDao.atualiza(pessoa);
            Mensagem.adicionarMensagemSucesso("Pessoa editada com sucesso.");
        }
        return 0;
    }

    @Transactional
    public void remover(Pessoa pessoa) {
        if (!matriculaDao.existeMatriculaParaPessoa(pessoa.getId())) {
            pessoaDao.deleta(pessoa);
            Mensagem.adicionarMensagemSucesso("Pessoa deletada com sucesso.");
        } else {
            Mensagem.adicionarMensagemErro("Erro", "Aluno contém matrículas em cursos.");
        }
    }

    public List<Pessoa> listar() {
        return pessoaDao.listar();
    }

    public Pessoa buscaPorId(Long id) {
        return pessoaDao.buscaPorId(id);
    }
}
