package br.unitins.datanext.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.unitins.datanext.application.JPAUtil;
import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.models.DefaultEntity;
import br.unitins.datanext.models.MarcaVentilacao;

public class Repository<T extends DefaultEntity<? super T>> {

	private EntityManager entityManager;
	private Repository<T> repository;

	
	public Repository() {
		this(JPAUtil.getEntityManager());
	}
	public Repository<T> getRepository() {
		return repository;
	}

	public void setRepository(Repository<T> repository) {
		this.repository = repository;
	}
	
	public Repository(EntityManager em) {
		setEntityManager(em);
	}
	
	public T save(T entity) throws RepositoryException {
		try { 
			return getEntityManager().merge(entity);
		} catch (Exception e) {
//			e.printStackTrace();
			throw new RepositoryException(e.getCause().getCause().getMessage());

		} 
	
	}

	public void remove(T entity) throws RepositoryException {
		
		try { 
			T t = getEntityManager().merge(entity);
			getEntityManager().remove(t);
		} catch (Exception e) {
			System.out.println("Erro ao Remover.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Remover. ");
		} 
	
	}
	
	public void beginTransaction() {
		try {
			getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void commitTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar um commit.");
		}
	}
	
	public void rollbackTransaction() {
		try {
			getEntityManager().getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public T findById(Integer id) {
		// obtendo o tipo da classe de forma generica (a classe deve ser publica)
		final ParameterizedType type = 
				(ParameterizedType) getClass().getGenericSuperclass();
		Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
		
		T t = (T) getEntityManager().find(tClass, id);
		
		return t;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
