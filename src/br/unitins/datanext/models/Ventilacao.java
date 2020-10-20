package br.unitins.datanext.models;

import javax.persistence.Entity;

@Entity
public class Ventilacao extends DefaultEntity {
	private String descricao;
	private Double velocidadeRpm;
	private Double kilowats;
	private MarcaVentilacao marca;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getVelocidadeRpm() {
		return velocidadeRpm;
	}

	public void setVelocidadeRpm(Double velocidadeRpm) {
		this.velocidadeRpm = velocidadeRpm;
	}

	public Double getKilowats() {
		return kilowats;
	}

	public void setKilowats(Double kilowats) {
		this.kilowats = kilowats;
	}

	public MarcaVentilacao getMarca() {
		return marca;
	}

	public void setMarca(MarcaVentilacao marca) {
		this.marca = marca;
	}

}
