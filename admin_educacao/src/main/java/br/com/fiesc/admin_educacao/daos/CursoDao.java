package br.com.fiesc.admin_educacao.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fiesc.admin_educacao.models.Curso;

@Stateless
public class CursoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void adiciona(Curso curso) {
		manager.persist(curso);		
	}
	
	public void atualiza(Curso curso) {
		manager.merge(curso);		
	}
	
	public void deleta(Curso curso) {
		manager.remove(manager.merge(curso));		
	}
	
	public List<Curso> listar() {
	    String jpql = "select c from Curso c";	
	    return manager.createQuery(jpql, Curso.class).getResultList();
	}

	public Curso buscaPorId(Long id) {
        return manager.find(Curso.class, id);
    }
	

}
