package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.ArmazenarGrao;

public class ArmazenarGraoRepository extends Repository<ArmazenarGrao>{
	public ArmazenarGraoRepository() {
		super();

	}
	
	public ArmazenarGraoRepository(EntityManager em) {
		super(em);

	}
	
	public List<ArmazenarGrao> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT a FROM ArmazenarGrao a ORDER BY a.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<ArmazenarGrao> findByInfo(Armazem agr) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  a ");
			jpql.append("FROM ");
			jpql.append("  ArmazenarGrao a ");
			jpql.append("WHERE ");
			jpql.append("  a.armazem.sigla = :sigla ");
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
