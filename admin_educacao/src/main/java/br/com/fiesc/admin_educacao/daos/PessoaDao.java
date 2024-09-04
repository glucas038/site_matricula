package br.com.fiesc.admin_educacao.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.fiesc.admin_educacao.models.Pessoa;

@Stateless
public class PessoaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void adiciona(Pessoa pessoa) {
		manager.persist(pessoa);		
	}
	
	public void atualiza(Pessoa pessoa) {
		manager.merge(pessoa);		
	}
	
	public void deleta(Pessoa pessoa) {
		manager.remove(manager.merge(pessoa));		
	}
	
	public List<Pessoa> listar() {
	    String jpql = "select p from Pessoa p";	
	    return manager.createQuery(jpql, Pessoa.class).getResultList();
	}
	
	public Pessoa buscarPorCpf(String cpf) {
        try {
        	String jpql = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf";
        	return manager.createQuery(jpql, Pessoa.class).setParameter("cpf", cpf).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

	public Pessoa buscaPorId(Long id) {
        return manager.find(Pessoa.class, id);
    }

}
