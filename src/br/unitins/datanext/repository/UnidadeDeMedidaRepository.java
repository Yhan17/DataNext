package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.UnidadeDeMedida;

public class UnidadeDeMedidaRepository extends Repository<UnidadeDeMedida>{
	public UnidadeDeMedidaRepository() {
		super();
	}
	
	public UnidadeDeMedidaRepository(EntityManager em) {
		super(em);
	}
	
	public List<UnidadeDeMedida> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT u FROM UnidadeDeMedida u ORDER BY u.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<UnidadeDeMedida> findByUn(String unidadeDeMedida) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  u ");
			jpql.append("FROM ");
			jpql.append("  UnidadeDeMedida u ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(u.unidadeDeMedida) like UPPER(:unidadeDeMedida) ");
			jpql.append("ORDER BY u.unidadeDeMedida ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("unidadeDeMedida", "%"+ unidadeDeMedida + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
}
