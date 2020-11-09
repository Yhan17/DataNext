package br.unitins.datanext.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unitins.datanext.models.Agricultor;
import br.unitins.datanext.repository.AgricultorRepository;

@WebServlet("/AgricultorImagem")
public class AgricultorImagem extends HttpServlet {

	private static final long serialVersionUID = -5526791067581229068L;

	public AgricultorImagem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return;
		}
		Agricultor agricultor = new AgricultorRepository().findById(Integer.parseInt(id));

		File f = new File(agricultor.getImagem());
		FileInputStream fis = new FileInputStream(f);
		byte[] arrayImagem = new byte[(int) f.length()];
		fis.read(arrayImagem);
		fis.close();
		response.getOutputStream().write(arrayImagem);
	}

}