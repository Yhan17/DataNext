package br.unitins.datanext.models;

public class Caminhao {
	private String placaCaminhao;
	private Double volume;
	private Integer tamanho;
	private Double Peso;
	private MarcaCaminhao marca;

	public String getPlacaCaminhao() {
		return placaCaminhao;
	}

	public void setPlacaCaminhao(String placaCaminhao) {
		this.placaCaminhao = placaCaminhao;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Double getPeso() {
		return Peso;
	}

	public void setPeso(Double peso) {
		Peso = peso;
	}

	public MarcaCaminhao getMarca() {
		return marca;
	}

	public void setMarca(MarcaCaminhao marca) {
		this.marca = marca;
	}

}
