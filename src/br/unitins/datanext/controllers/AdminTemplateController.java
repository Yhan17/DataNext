package br.unitins.datanext.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.datanext.application.Session;
import br.unitins.datanext.models.Usuario;

@Named("adminTemplateController")
@ViewScoped
public class AdminTemplateController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) // buscando o usuario da sessao
			usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");	
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	

	public String encerrarSessao() {
		// encerrando a sessao
		Session.getInstance().invalidateSession();
		return "/pages/index.xhtml?faces-redirect=true";
	}

}
