package br.unitins.datanext.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unitins.datanext.models.Armazem;
import br.unitins.datanext.repository.ArmazemRepository;

@WebServlet("/ArmazemImagem")
public class ArmazemImagem extends HttpServlet {

	private static final long serialVersionUID = -5526791067581229068L;

	public ArmazemImagem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return;
		}
		Armazem armazem = new ArmazemRepository().findById(Integer.parseInt(id));

		File f = new File(armazem.getImagem());
		FileInputStream fis = new FileInputStream(f);
		byte[] arrayImagem = new byte[(int) f.length()];
		fis.read(arrayImagem);
		fis.close();
		response.getOutputStream().write(arrayImagem);
	}

}