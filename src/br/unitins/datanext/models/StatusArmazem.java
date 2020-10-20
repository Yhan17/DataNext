package br.unitins.datanext.models;

public enum StatusArmazem {
	VAZIO(1,"Vazio"),
	PARCIALMENTECHEIO(2,"Parcialmente Cheio"),
	PARCIALMENTEVAZIO(3,"Parcialmemte Vazio"),
	CHEIO(4,"Cheio");
	
	private int id;
	private String label;
	
	private StatusArmazem(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static StatusArmazem valueOf(int valor) {
		for (StatusArmazem statusArmazem : StatusArmazem.values()) {
			if (valor == statusArmazem.getId())
				return statusArmazem;
		} 
		return null;
	}
}
