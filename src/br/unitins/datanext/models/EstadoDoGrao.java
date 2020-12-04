package br.unitins.datanext.models;

public enum EstadoDoGrao {
	UMIDOESECO(0,"Umido e Limpo"),
	SECOESUJO(1,"Seco e Sujo"),
	UMIDOESUJO(2,"Umido e Sujo"),
	SECOELIMPO(3,"Seco e Limpo");
	
	private int id;
	private String label;

	private EstadoDoGrao(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static EstadoDoGrao valueOf(int valor) {
		for (EstadoDoGrao estadoGrao : EstadoDoGrao.values()) {
			if (valor == estadoGrao.getId())
				return estadoGrao;
		} 
		return null;
	}
}
