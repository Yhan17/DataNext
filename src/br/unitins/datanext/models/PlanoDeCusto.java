package br.unitins.datanext.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class PlanoDeCusto extends DefaultEntity {
	private Date dataContabilidade;
	private Boolean bloqueado;
	private Boolean obrigatorio;
	private Double preco;
	private String descricao;
	private List<PlanoDeCusto> dependentes;
	private TipoConta tipoConta;

	public Date getDataContabilidade() {
		return dataContabilidade;
	}

	public void setDataContabilidade(Date dataContabilidade) {
		this.dataContabilidade = dataContabilidade;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Boolean getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(Boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<PlanoDeCusto> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<PlanoDeCusto> dependentes) {
		this.dependentes = dependentes;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

}
