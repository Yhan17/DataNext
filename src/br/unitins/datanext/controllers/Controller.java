package br.unitins.datanext.controllers;

import java.io.Serializable;

import br.unitins.datanext.application.RepositoryException;
import br.unitins.datanext.application.Util;
import br.unitins.datanext.models.DefaultEntity;
import br.unitins.datanext.repository.Repository;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {

	private static final long serialVersionUID = 3094966366555764771L;
	
	protected T entity;

	public Controller() {
		super();
	}

	public String salvar() {
		
		Repository<T> repo = new Repository<T>();
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			Util.addInfoMessage("Operação realizada com sucesso.");
			return null;
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
		return null;
		
	}

	public void excluir(T obj) {
		Repository<T> repo = new Repository<T>();
		try {
			repo.beginTransaction();
			repo.remove(obj);
			Util.addInfoMessage("Remoção realizada com sucesso.");
			repo.commitTransaction();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao excluir.");
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
		}
		
		limpar();
	}
	
	public String editar(T entity) {
		setEntity(entity);
		return null;
	}
	
	public void limpar() {
		setEntity(null);
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	

}