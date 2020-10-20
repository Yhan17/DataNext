package br.unitins.datanext.models;

public enum TipoConta {
	DEVEDORA(1,"Devedora"),
	CREDORA(2,"Credora");
	
	private int id;
	private String label;
	
	private TipoConta(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static TipoConta valueOf(int valor) {
		for (TipoConta tipoConta : TipoConta.values()) {
			if (valor == tipoConta.getId())
				return tipoConta;
		} 
		return null;
	}
}
