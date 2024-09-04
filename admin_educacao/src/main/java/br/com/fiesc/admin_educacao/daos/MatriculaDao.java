package br.com.fiesc.admin_educacao.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.fiesc.admin_educacao.models.Matricula;

@Stateless
public class MatriculaDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Matricula matricula) {
		manager.persist(matricula);
	}

	public void atualiza(Matricula matricula) {
		manager.merge(matricula);
	}

	public void deleta(Matricula matricula) {
		manager.remove(manager.merge(matricula));
	}

	public List<Matricula> listar() {
		String jpql = "select m from Matricula m";
		return manager.createQuery(jpql, Matricula.class).getResultList();
	}

	public Matricula buscaPorId(Long id) {
		return manager.find(Matricula.class, id);
	}

	public Long contarMatriculasNoCurso(Long cursoId) {
		String jpql = "SELECT COUNT(m) FROM Matricula m WHERE m.curso.id = :cursoId";
		Long count = manager.createQuery(jpql, Long.class).setParameter("cursoId", cursoId).getSingleResult();
		return count;
	}

	public boolean existeMatriculaParaPessoa(Long pessoaId) {
		String jpql = "SELECT COUNT(m) FROM Matricula m WHERE m.pessoa.id = :pessoaId";
		Long count = manager.createQuery(jpql, Long.class).setParameter("pessoaId", pessoaId).getSingleResult();
		return count > 0;
	}

	public Matricula buscarPorAlunoECurso(Long cursoId, Long pessoaId) {
		try {
			return manager
					.createQuery("SELECT m FROM Matricula m WHERE m.pessoa.id = :pessoaId AND m.curso.id = :cursoId",
							Matricula.class)
					.setParameter("pessoaId", pessoaId).setParameter("cursoId", cursoId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
