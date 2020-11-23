package br.unitins.datanext.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

@Entity
public class PlanoDeCusto extends DefaultEntity<PlanoDeCusto> {
	private Date dataContabilidade;
	private Boolean bloqueado;
	private Boolean obrigatorio;
	private Double preco;
	private String descricao;
	@ManyToMany(cascade=CascadeType.PERSIST) 
	@JoinTable(name="PlanoDeCustoRelationShip", joinColumns = {@JoinColumn(name="plano_id")}, inverseJoinColumns={@JoinColumn(name="dependente_id")})  
	@OrderColumn
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
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bloqueado == null) ? 0 : bloqueado.hashCode());
		result = prime * result + ((dataContabilidade == null) ? 0 : dataContabilidade.hashCode());
		result = prime * result + ((dependentes == null) ? 0 : dependentes.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((obrigatorio == null) ? 0 : obrigatorio.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((tipoConta == null) ? 0 : tipoConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoDeCusto other = (PlanoDeCusto) obj;
		if (bloqueado == null) {
			if (other.bloqueado != null)
				return false;
		} else if (!bloqueado.equals(other.bloqueado))
			return false;
		if (dataContabilidade == null) {
			if (other.dataContabilidade != null)
				return false;
		} else if (!dataContabilidade.equals(other.dataContabilidade))
			return false;
		if (dependentes == null) {
			if (other.dependentes != null)
				return false;
		} else if (!dependentes.equals(other.dependentes))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (obrigatorio == null) {
			if (other.obrigatorio != null)
				return false;
		} else if (!obrigatorio.equals(other.obrigatorio))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (tipoConta != other.tipoConta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanoDeCusto [dataContabilidade=" + dataContabilidade + ", bloqueado=" + bloqueado + ", obrigatorio="
				+ obrigatorio + ", preco=" + preco + ", descricao=" + descricao + ", dependentes=" + dependentes
				+ ", tipoConta=" + tipoConta + " id="+getId()+" ]";
	}
	
	

}
