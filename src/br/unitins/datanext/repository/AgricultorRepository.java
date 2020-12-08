package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Agricultor;


public class AgricultorRepository extends Repository<Agricultor>{

	public AgricultorRepository() {
		super();

	}
	
	public AgricultorRepository(EntityManager em) {
		super(em);

	}

	public List<Agricultor> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT a FROM Agricultor a ORDER BY a.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Agricultor> findByInfo(String agr) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  a ");
			jpql.append("FROM ");
			jpql.append("  Agricultor a ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(a.pessoa.nome) like UPPER(:nome) ");
			jpql.append("  OR a.pessoa.nif like :cpf ");
			jpql.append("  ORDER BY a.id ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ agr + "%" );
			query.setParameter("cpf",  "%"+ agr + "%" );
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
}
