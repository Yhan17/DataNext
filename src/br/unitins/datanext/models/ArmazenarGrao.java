package br.unitins.datanext.models;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ArmazenarGrao extends DefaultEntity<ArmazenarGrao> {
	private LocalTime horarioArmazenagem;
	private EstadoDoGrao estadoDoGrao;
	private Double umidadeRelativaDoAr;
	private Double pressaoVaporGrao;
	private Double pressaoVaporAr;
	private Double temperatura;
	private String condicaoArmazenagem;
	private String etapaArmazenamento;
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

	public LocalTime getHorarioArmazenagem() {
		return horarioArmazenagem;
	}

	public void setHorarioArmazenagem(LocalTime horarioArmazenagem) {
		this.horarioArmazenagem = horarioArmazenagem;
	}

	public EstadoDoGrao getEstadoDoGrao() {
		return estadoDoGrao;
	}

	public void setEstadoDoGrao(EstadoDoGrao estadoDoGrao) {
		this.estadoDoGrao = estadoDoGrao;
	}

	public Double getUmidadeRelativaDoAr() {
		return umidadeRelativaDoAr;
	}

	public void setUmidadeRelativaDoAr(Double umidadeRelativaDoAr) {
		this.umidadeRelativaDoAr = umidadeRelativaDoAr;
	}

	public Double getPressaoVaporGrao() {
		return pressaoVaporGrao;
	}

	public void setPressaoVaporGrao(Double pressaoVaporGrao) {
		this.pressaoVaporGrao = pressaoVaporGrao;
	}

	public Double getPressaoVaporAr() {
		return pressaoVaporAr;
	}

	public void setPressaoVaporAr(Double pressaoVaporAr) {
		this.pressaoVaporAr = pressaoVaporAr;
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
