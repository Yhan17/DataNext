package br.unitins.datanext.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class DefaultEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;
	
	@PrePersist
	private void atualizaDataCriacao() {
		created_at = new Date();
	}

	@PreUpdate
	private void atualizaDataAtualizacao() {
		updated_at = new Date();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return created_at;
	}

	public void setDataCriacao(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDataAtualizacao() {
		return updated_at;
	}

	public void setDataAtualizacao(Date updated_at) {
		this.updated_at = updated_at;
	}
	
}	
