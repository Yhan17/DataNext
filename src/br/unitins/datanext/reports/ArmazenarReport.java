package br.unitins.datanext.reports;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.datanext.application.JDBCUtil;
import br.unitins.datanext.application.ReportServlet;

@WebServlet("/armazenarreport")
public class ArmazenarReport extends ReportServlet {

	private static final long serialVersionUID = -3051617186047680331L;

	@Override
	public String getArquivoJasper() {
		return "Coffee.jasper";

	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		 HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
		 param.put("ID_ARMAZENAGEM", Integer.class);
		 return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();

	}

}
