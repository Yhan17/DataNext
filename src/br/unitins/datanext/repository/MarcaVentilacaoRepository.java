package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.MarcaVentilacao;

public class MarcaVentilacaoRepository extends Repository<MarcaVentilacao> {
	

	
	public MarcaVentilacaoRepository() {
		super();
	}
	
	public MarcaVentilacaoRepository(EntityManager em) {
		super(em);
	}
	
	public List<MarcaVentilacao> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT m FROM MarcaVentilacao m ORDER BY m.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<MarcaVentilacao> findByMarca(String marca) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  m ");
			jpql.append("FROM ");
			jpql.append("  MarcaVentilacao m ");
			jpql.append("WHERE ");
			jpql.append("  UPPER(m.marca) like UPPER(:marca) ");
			jpql.append("ORDER BY m.marca ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("marca", "%"+ marca + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
}
