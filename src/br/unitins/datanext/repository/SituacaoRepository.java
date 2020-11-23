package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.models.Situacao;

public class SituacaoRepository extends Repository<Situacao>{
	public SituacaoRepository() {
		super();
	}
	public SituacaoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Situacao> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT s FROM Situacao s ORDER BY s.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Situacao> findByArmazem(Armazem armazem) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  s ");
			jpql.append("FROM ");
			jpql.append("  Situacao s ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(s.armazem.sigla) like UPPER(:sigla) ");
			jpql.append("ORDER BY s.id ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("sigla", "%"+ armazem.getSigla() + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
}
