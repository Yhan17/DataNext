package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Ventilacao;

public class VentilacaoRepository extends Repository<Ventilacao> {
	

	
	public VentilacaoRepository() {
		super();
	}
	
	public VentilacaoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Ventilacao> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT v FROM Ventilacao v ORDER BY v.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Ventilacao> findByMarca(String marca) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  v ");
			jpql.append("FROM ");
			jpql.append("  Ventilacao v ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(v.nome) like UPPER(:nome) ");
			jpql.append("ORDER BY v.nome ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("nome", "%"+ marca + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
}
