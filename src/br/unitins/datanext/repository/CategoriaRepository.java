package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Categoria;

public class CategoriaRepository extends Repository<Categoria>{
	public CategoriaRepository() {
		super();
	}
	
	public CategoriaRepository(EntityManager em) {
		super(em);
	}
	
	public List<Categoria> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT c FROM Categoria c ORDER BY c.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Categoria> findByUn(String sigla) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  c ");
			jpql.append("FROM ");
			jpql.append("  Categoria c ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(c.sigla) like UPPER(:sigla) ");
			jpql.append("ORDER BY c.sigla ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("sigla", "%"+ sigla + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
}