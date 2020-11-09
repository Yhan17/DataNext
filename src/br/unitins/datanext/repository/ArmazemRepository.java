package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Armazem;


public class ArmazemRepository extends Repository<Armazem>{

	public ArmazemRepository() {
		super();

	}
	
	public ArmazemRepository(EntityManager em) {
		super(em);

	}

	public List<Armazem> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT a FROM Armazem a ORDER BY a.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Armazem> findByInfo(Armazem agr) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  a ");
			jpql.append("FROM ");
			jpql.append("  Armazem a ");
			jpql.append("WHERE ");
			jpql.append("  a.sigla = :sigla ");
			jpql.append("  ORDER BY a.id ");
			
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
