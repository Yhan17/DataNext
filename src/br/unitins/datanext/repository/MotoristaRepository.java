package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Agricultor;
import br.unitins.datanext.models.Motorista;


public class MotoristaRepository extends Repository<Motorista>{

	public MotoristaRepository() {
		super();

	}
	
	public MotoristaRepository(EntityManager em) {
		super(em);

	}

	public List<Motorista> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT m FROM Motorista m ORDER BY m.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Motorista> findByInfo(Motorista mot) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  m ");
			jpql.append("FROM ");
			jpql.append("  Motorista m ");
			jpql.append("WHERE ");
			jpql.append("  m.pessoa.nome = :nome ");
			jpql.append("  ORDER BY m.id ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome",  mot.getPessoa().getNome()  );
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
}
