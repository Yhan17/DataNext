package br.unitins.datanext.models;

public enum MarcaCaminhao {
	VOLKSWAGEN(0, "Volkswagen"),
	MERCEDESBENZ(1, "Mercedes Bens"),
	VOLVO(2, "Volvo"),
	SCANIA(3, "Scania"),
	FORD(4, "Ford"),
	IVECO(5, "Iveco"),
	HYUNDAI(6, "Hyundai"),
	MAN(7, "Man"),
	AGRALE(8, "Agrale"),
	EFFA(9, "Effa"),
	INTERNATIONAL(10, "International"),
	SINOTRUCK(11, "Sinotruck"),
	SHACMAN(12, "Shacman"),
	EXTERNA(13, "Externa");
	
	private int id;
	private String label;
	
	private MarcaCaminhao(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static MarcaCaminhao valueOf(int valor) {
		for (MarcaCaminhao marcaCaminhao : MarcaCaminhao.values()) {
			if (valor == marcaCaminhao.getId())
				return marcaCaminhao;
		} 
		return null;
	}
}
