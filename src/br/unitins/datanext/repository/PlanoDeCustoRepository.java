package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.PlanoDeCusto;

public class PlanoDeCustoRepository extends Repository<PlanoDeCusto> {
	

	
	public PlanoDeCustoRepository() {
		super();
	}
	
	public PlanoDeCustoRepository(EntityManager em) {
		super(em);
	}
	
	public List<PlanoDeCusto> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT p FROM PlanoDeCusto p ORDER BY p.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<PlanoDeCusto> findByDesc(String plano) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  p ");
			jpql.append("FROM ");
			jpql.append("  PlanoDeCusto p ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(p.descricao) like UPPER(:desc) ");
			jpql.append("ORDER BY p.descricao ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("desc", "%"+ plano + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}

}
