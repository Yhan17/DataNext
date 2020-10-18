package br.unitins.datanext.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("unidadeDeMedidaRegisterController")
@ViewScoped
public class UnidadeDeMedidaRegisterController implements Serializable {

	private static final long serialVersionUID = -3013768893328582676L;
	private String messagem = "Teste";

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
