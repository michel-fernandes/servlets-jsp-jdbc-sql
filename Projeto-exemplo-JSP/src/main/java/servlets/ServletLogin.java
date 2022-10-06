package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.LoginDAORepository;

/*controller, ServeletLoginController*/
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDAORepository loginDAO = new LoginDAORepository();
       
    public ServletLogin() {

    }
    
    /*Recebe os dados da url em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); //invalida a sessao
			request.setAttribute("msg", "Sessão encerrada");
			request.getRequestDispatcher("/index.jsp?").forward(request, response);
		} else {
			doPost(request, response);			
		}
		
	}
	
	/*Recebe os dados enviados por um formulário*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String senha = request.getParameter("senha");
			String login = request.getParameter("login");
			String url = request.getParameter("url");
			
			if(!login.isEmpty() && login!= null && !senha.isEmpty() && senha!= null) {
				ModelLogin modelLogin = new ModelLogin(login, senha);
				
				if(loginDAO.autenticar(modelLogin)) {
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					
					if(url==null || url.equals("null")) {
						url = "/principal/principal.jsp";
					}
					request.getRequestDispatcher(url).forward(request, response);
				} else {
					request.setAttribute("msg", "Login ou senha incorreto");
					request.getRequestDispatcher("/index.jsp?").forward(request, response);
				}
			}else {
				request.setAttribute("msg", "Por favor, fazer login");
				request.getRequestDispatcher("/index.jsp?").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			erro(e.getMessage(), request, response);
		} 		
	}
	
	public void erro(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
	}
}
