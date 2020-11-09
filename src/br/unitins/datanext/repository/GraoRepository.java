package br.unitins.datanext.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.Categoria;
import br.unitins.datanext.models.Grao;
import br.unitins.datanext.models.UnidadeDeMedida;

public class GraoRepository extends Repository<Grao> {
	public GraoRepository() {
		super();
	}
	
	public GraoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Grao> findAll() throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT g FROM Grao g ORDER BY g.id");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
	public List<Grao> findByInfo(UnidadeDeMedida un, Categoria cat) throws RepositoryException{ 
		try {
			EntityManager em = JPAUtil.getEntityManager();
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  g ");
			jpql.append("FROM ");
			jpql.append("  Grao g ");
			jpql.append("WHERE ");
			jpql.append("  g.unidadeDeMedida.id = :un AND g.categoria.id = :cat");
			jpql.append("  ORDER BY g.id ");
			
			Query query = em.createQuery(jpql.toString());
			query.setParameter("un",  un.getId()  );
			query.setParameter("cat", cat.getId()  );
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
		
	}
	
}
