package br.unitins.datanext.models;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ArmazenarGrao extends DefaultEntity<ArmazenarGrao> {
	private Time horarioArmazenagem;
	private String estadoDoGrao;
	private String umidadeRelativaDoAr;
	private String pressaoVaporGrao;
	private String prassaoVaporAr;
	private Double temperatura;
	private String condicaoArmazenagem;
	private String etapaArmazenamento;
	private Double custoArmazenagem;
	private Double quantidadeArmazenada;
	@ManyToOne
	private Motorista motorista;
	@ManyToOne
	private Grao grao;
	@ManyToOne
	private Armazem armazem;
	@ManyToOne
	private Agricultor agricultor;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Endereco endereco;

	public Time getHorarioArmazenagem() {
		return horarioArmazenagem;
	}

	public void setHorarioArmazenagem(Time horarioArmazenagem) {
		this.horarioArmazenagem = horarioArmazenagem;
	}

	public String getEstadoDoGrao() {
		return estadoDoGrao;
	}

	public void setEstadoDoGrao(String estadoDoGrao) {
		this.estadoDoGrao = estadoDoGrao;
	}

	public String getUmidadeRelativaDoAr() {
		return umidadeRelativaDoAr;
	}

	public void setUmidadeRelativaDoAr(String umidadeRelativaDoAr) {
		this.umidadeRelativaDoAr = umidadeRelativaDoAr;
	}

	public String getPressaoVaporGrao() {
		return pressaoVaporGrao;
	}

	public void setPressaoVaporGrao(String pressaoVaporGrao) {
		this.pressaoVaporGrao = pressaoVaporGrao;
	}

	public String getPrassaoVaporAr() {
		return prassaoVaporAr;
	}

	public void setPrassaoVaporAr(String prassaoVaporAr) {
		this.prassaoVaporAr = prassaoVaporAr;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public String getCondicaoArmazenagem() {
		return condicaoArmazenagem;
	}

	public void setCondicaoArmazenagem(String condicaoArmazenagem) {
		this.condicaoArmazenagem = condicaoArmazenagem;
	}

	public String getEtapaArmazenamento() {
		return etapaArmazenamento;
	}

	public void setEtapaArmazenamento(String etapaArmazenamento) {
		this.etapaArmazenamento = etapaArmazenamento;
	}

	public Double getCustoArmazenagem() {
		return custoArmazenagem;
	}

	public void setCustoArmazenagem(Double custoArmazenagem) {
		this.custoArmazenagem = custoArmazenagem;
	}

	public Double getQuantidadeArmazenada() {
		return quantidadeArmazenada;
	}

	public void setQuantidadeArmazenada(Double quantidadeArmazenada) {
		this.quantidadeArmazenada = quantidadeArmazenada;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Grao getGrao() {
		return grao;
	}

	public void setGrao(Grao grao) {
		this.grao = grao;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

	public Agricultor getAgricultor() {
		return agricultor;
	}

	public void setAgricultor(Agricultor agricultor) {
		this.agricultor = agricultor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
