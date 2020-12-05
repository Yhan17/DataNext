package br.unitins.datanext.reports;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.datanext.application.JDBCUtil;
import br.unitins.datanext.application.ReportServlet;

@WebServlet("/armazemreport")
public class ArmazemReport extends ReportServlet {


	private static final long serialVersionUID = 4698158525123222343L;

	@Override
	public String getArquivoJasper() {
		return "Coffee_Landscape.jasper";

	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();

	}

}
