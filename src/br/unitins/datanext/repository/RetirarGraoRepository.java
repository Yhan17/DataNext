package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.RetirarGrao;

public class RetirarGraoRepository extends Repository<RetirarGrao>{
	
	public RetirarGraoRepository() {
		super();

	}
	
	public RetirarGraoRepository(EntityManager em) {
		super(em);

	}
	
	public List<RetirarGrao> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT r FROM RetirarGrao r ORDER BY r.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<RetirarGrao> findByInfo(Armazem agr) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  r ");
			jpql.append("FROM ");
			jpql.append("  RetirarGrao r ");
			jpql.append("WHERE ");
			jpql.append("  r.armazem.sigla = :sigla ");
			jpql.append("  ORDER BY r.id ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("sigla",  agr.getSigla()  );
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
}
