package br.unitins.datanext.security;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.datanext.models.Usuario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/pages/*"})
public class SecurityFilter implements Filter{
	
	private List<String> paginasComPermissao;

	
	protected List<String> getPaginasComPermissao() {
		if(paginasComPermissao == null) {
			paginasComPermissao = new ArrayList<String>();
			paginasComPermissao.add("/DataNext/pages/adminHome.xhtml");
			paginasComPermissao.add("/DataNext/pages/agricultor.xhtml");
			paginasComPermissao.add("/DataNext/pages/agricultorForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/armazem.xhtml");
			paginasComPermissao.add("/DataNext/pages/armazemForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/armazenarGraos.xhtml");
			paginasComPermissao.add("/DataNext/pages/armazenarGraosForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/categoria.xhtml");
			paginasComPermissao.add("/DataNext/pages/categoriaForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/contas.xhtml");
			paginasComPermissao.add("/DataNext/pages/contasForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/graos.xhtml");
			paginasComPermissao.add("/DataNext/pages/graosForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/historico.xhtml");
			paginasComPermissao.add("/DataNext/pages/index.xhtml");
			paginasComPermissao.add("/DataNext/pages/login.xhtml");
			paginasComPermissao.add("/DataNext/pages/mapa.xhtml");
			paginasComPermissao.add("/DataNext/pages/marca.xhtml");
			paginasComPermissao.add("/DataNext/pages/marcaForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/motorista.xhtml");
			paginasComPermissao.add("/DataNext/pages/motoristaForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/planodecustolisting.xhtml");
			paginasComPermissao.add("/DataNext/pages/profile.xhtml");
			paginasComPermissao.add("/DataNext/pages/register.xhtml");
			paginasComPermissao.add("/DataNext/pages/retirarGraos.xhtml");
			paginasComPermissao.add("/DataNext/pages/retirarGraosForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/unidadedemedida.xhtml");
			paginasComPermissao.add("/DataNext/pages/unidadedemedidaForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/usuario.xhtml");
			paginasComPermissao.add("/DataNext/pages/usuarioForm.xhtml");
			paginasComPermissao.add("/DataNext/pages/ventilacao.xhtml");
			paginasComPermissao.add("/DataNext/pages/ventilacaoForm.xhtml");
		}
		return paginasComPermissao;
	}


	protected void setPaginasComPermissao(List<String> paginasComPermissao) {
		this.paginasComPermissao = paginasComPermissao;
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
// 	 	Para desabilitar o filter, descomente as duas proximas linhas e comente o restante		
//		chain.doFilter(request, response);
//		return;

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		// imprime o endereco da pagina
		String endereco = servletRequest.getRequestURI();
		System.out.println(endereco);
//	    ex.	/Vendas/faces/pages/usuario.xhtml


		if(endereco.equals("/DataNext/pages/index.xhtml") || endereco.equals("/DataNext/pages/login.xhtml")) {
			chain.doFilter(request, response);
			return;
		}
		// filtrando o nome da pagina
//		if (endereco != null) {
//			int inicio = endereco.lastIndexOf("/DataNext/") + 7;
//			int fim = endereco.length();
//			endereco = endereco.substring(inicio, fim);
//		}
		System.out.println(endereco);
//		ex.   pages/usuario.xhtml

		// retorna a sessao corrente (false - para nao criar uma nova sessao)
		HttpSession session = servletRequest.getSession(false);

		Usuario usuario = null;
		if (session != null)
			usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (usuario == null) {
			((HttpServletResponse) response).sendRedirect("/DataNext/pages/index.xhtml");
		}  else {
			if(endereco.equals("/pages/login.xhtml"))
				((HttpServletResponse) response).sendRedirect("/DataNext/pages/adminHome.xhtml");
			if (getPaginasComPermissao().contains(endereco)) {
				// segue o fluxo 
				chain.doFilter(request, response);
				return;
			} else {
				((HttpServletResponse) response).sendRedirect("/DataNext/pages/index.xhtml");
			}
		}
		
	}

}
