package br.unitins.datanext.models;

public class GrainApi {
	private ApiBody dataset;

	public ApiBody getDataset() {
		return dataset;
	}

	public void setDataset(ApiBody dataset) {
		this.dataset = dataset;
	}

	@Override
	public String toString() {
		return "TestObject [dataset=" + dataset + "]";
	}

	
	
}
