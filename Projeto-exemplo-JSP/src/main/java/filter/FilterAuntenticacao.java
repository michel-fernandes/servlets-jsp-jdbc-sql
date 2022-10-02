package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todos as requisições do mapeamento
public class FilterAuntenticacao extends HttpFilter implements Filter {

	public FilterAuntenticacao() {
		super();
	}

	// encerra os processos quando o servidor é parado
	public void destroy() {

	}

	/*
	 * intercepta as requicoes e as resposatas validacao de autenticacao dar commit
	 * e rollback de transacoes no banco validar e fazer redirecionamento de paginas
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {//nao ea logado
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlParaAutenticar = req.getServletPath(); // url que está sendo acessado
		/* validar se esta logado senao redireciona para tela de login */
		if ((usuarioLogado == null || usuarioLogado.isEmpty()) 
				&& !urlParaAutenticar.contains("/principal/ServletLogin")) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor, fazer login");
			redirecionar.forward(request, response);
			return; //para a execucao e redireciona para o login
		}

		chain.doFilter(request, response);
	}

	// inicia os processos quando o servidor é iniciado
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
